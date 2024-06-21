package com.maider.article.infraestructure.mappers;

import com.maider.article.domain.entities.User;
import com.maider.article.domain.entities.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getRoles());
    }
}
