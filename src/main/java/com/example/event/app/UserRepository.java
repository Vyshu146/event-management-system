package com.example.event.app;



//import com.example.event.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // later we can add methods like findByEmail
}
