package com.maider.article.domain.services;

import com.maider.article.domain.entities.User;
import com.maider.article.domain.repositories.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepositoy userRepositoy;
    public User getUser(String name) {
        return userRepositoy.findByUsername(name);
    }
    public void save(User user) {
        userRepositoy.save(user);
    }
}
