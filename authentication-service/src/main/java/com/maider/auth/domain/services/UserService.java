package com.maider.auth.domain.services;

import com.maider.auth.domain.entities.dto.UserDTO;
import com.maider.auth.domain.entities.User;
import com.maider.auth.domain.publisher.EventPublisher;
import com.maider.auth.domain.repositories.UserRepository;
import com.maider.auth.result.Failure;
import com.maider.auth.result.Result;
import com.maider.auth.result.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventPublisher eventPublisher;
    @Autowired
    PasswordEncoder passwordEncoder;
    public Result<User, String> createUser (User user) {
        try {
            String passwordEncrypted = passwordEncoder.encode(user.getPassword());
            User newUser = new User(user.getUsername(), passwordEncrypted, user.getRoles());
            User userCreated = userRepository.save(newUser);
            UserDTO userDTO = new UserDTO(userCreated.getUsername(), userCreated.getId(), userCreated.getRoles());
            eventPublisher.send(userDTO);
            return new Success<>(userCreated);
        }catch (DataIntegrityViolationException e) {
            return new Failure<>("The name is already taken");
        }catch (Exception e) {
            return new Failure<>("Error relationed with database");
        }
    }
}
