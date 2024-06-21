package com.maider.article.domain.repositories;

import com.maider.article.domain.entities.User;

public interface UserRepositoy {
    User findByUsername(String name);
    User save(User user);
}
