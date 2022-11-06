package ru.kata.spring.boot_security.demo.dao;





import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    User findByUsername(String username);
}
