package com.maider.auth.domain.publisher;


import com.maider.auth.domain.entities.dto.UserDTO;

public interface EventPublisher {
    void send(UserDTO user);
}
