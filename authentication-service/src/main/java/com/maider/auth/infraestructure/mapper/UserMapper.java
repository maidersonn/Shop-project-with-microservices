package com.maider.auth.infraestructure.mapper;

import com.maider.auth.domain.entities.dto.UserCreationDTO;
import com.maider.auth.domain.entities.dto.UserDTO;
import com.maider.auth.domain.entities.User;

public class UserMapper {
    public static User toUser(UserCreationDTO userCreationDTO) {
        return new User(userCreationDTO.getUsername(), userCreationDTO.getPassword(), userCreationDTO.getRoles());
    }
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getId(), user.getRoles());
    }
}
