package DemoFirst;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // Sets up a simulated HTTP client to call your endpoints
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldRejectInvalidUserWith400() throws Exception {
		String badJson = """
                {
                  "name": "",
                  "email": "not-an-email"
                }
                """;

		mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(badJson))
				.andExpect(status().isBadRequest()); // Expects 400 Bad Request
	}

	@Test
	void shouldCreateAndFetchUser() throws Exception {
		String validJson = """
                {
                  "name": "Alice",
                  "email": "alice@example.com"
                }
                """;

		// 1. Test POST /users -> Expect 200 OK and returned JSON with name "Alice"
		mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(validJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Alice"))
				.andExpect(jsonPath("$.email").value("alice@example.com"));

		// 2. Test GET /users -> Expect 200 OK and "Alice" in the returned list
		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Alice"));
	}
}
