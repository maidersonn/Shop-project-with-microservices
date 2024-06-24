package com.maider.auth.infraestructure.database;

import com.maider.auth.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User,String> {

    User findByUsername(String username);
}
