package com.maider.authenticationMicroService.controllers.mapper;

import com.maider.authenticationMicroService.controllers.dto.UserCrationDTO;
import com.maider.authenticationMicroService.controllers.dto.UserDTO;
import com.maider.authenticationMicroService.domain.entities.User;

public class UserMapper {
    public static User toUser(UserCrationDTO userCreationDTO) {
        return new User(userCreationDTO.getUsername(), userCreationDTO.getPassword(), userCreationDTO.getRoles());
    }
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getId(), user.getRoles());
    }
}
