package com.maider.authenticationMicroService.infraestructure;

import com.maider.authenticationMicroService.domain.entities.User;
import com.maider.authenticationMicroService.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImplementation implements UserRepository {
    @Autowired
    private JpaUserRepository jpaRepo;
    @Override
    public User findByUsername(String username) {
        return jpaRepo.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return jpaRepo.save(user);
    }
}
