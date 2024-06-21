package com.maider.authenticationMicroService.domain.repositories;

import com.maider.authenticationMicroService.domain.entities.User;

public interface UserRepository {
    User findByUsername(String username);
    User save(User user);
}
