package com.maider.article.config;

import com.maider.article.domain.entities.dto.UserDTO;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class UserDtoDeserializar extends JsonDeserializer<UserDTO> {
}
