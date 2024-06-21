package com.maider.article.infraestructure.database;

import com.maider.article.domain.entities.User;
import com.maider.article.domain.repositories.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplementation implements UserRepositoy {
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Override
    public User findByUsername(String name) {
        return jpaUserRepository.findByUsername(name);
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }
}
