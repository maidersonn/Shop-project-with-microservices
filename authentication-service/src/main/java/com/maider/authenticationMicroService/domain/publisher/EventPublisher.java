package com.maider.authenticationMicroService.domain.publisher;


import com.maider.authenticationMicroService.controllers.dto.UserDTO;

public interface EventPublisher {
    void send(UserDTO user);
}
