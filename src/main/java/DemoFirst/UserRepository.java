package DemoFirst;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring automatically generates save(), findAll(), findById(), deleteById()!
}
