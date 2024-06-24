package com.maider.auth.domain.repositories;

import com.maider.auth.domain.entities.User;

public interface UserRepository {
    User findByUsername(String username);
    User save(User user);
}
