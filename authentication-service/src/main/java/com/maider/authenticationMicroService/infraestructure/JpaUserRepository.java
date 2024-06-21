package com.maider.authenticationMicroService.infraestructure;

import com.maider.authenticationMicroService.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User,String> {

    User findByUsername(String username);
}
