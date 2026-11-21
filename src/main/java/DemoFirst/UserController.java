package DemoFirst;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. View all users: GET http://localhost:8080/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 2. Create a user with @Valid protection: POST http://localhost:8080/users
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    // 3. Update an existing user: PUT http://localhost:8080/users/1
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // 4. Delete a user: DELETE http://localhost:8080/users/1
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
