package com.maider.authenticationMicroService.infraestructure.kafka;

import com.maider.authenticationMicroService.controllers.dto.UserDTO;
import com.maider.authenticationMicroService.domain.publisher.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherImplementation implements EventPublisher {
    @Autowired
    KafkaTemplate<String, UserDTO> kafkaTemplate;

    @Override
    public void send(UserDTO user) {
        kafkaTemplate.send("newUser", user);
    }
}
