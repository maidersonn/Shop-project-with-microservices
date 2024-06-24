package com.maider.auth.infraestructure.controllers;

import com.maider.auth.domain.entities.dto.UserCreationDTO;
import com.maider.auth.domain.entities.dto.UserDTO;
import com.maider.auth.infraestructure.mapper.UserMapper;
import com.maider.auth.domain.entities.User;
import com.maider.auth.domain.services.UserService;
import com.maider.auth.result.Result;
import com.maider.auth.result.Success;
import com.maider.auth.config.JWTAuthenticationConfig;
import com.maider.auth.config.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("user") String username, @RequestParam("password") String password) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtAuthenticationConfig.getJWTToken(userDetails.getUsername());
        return new ResponseEntity<>(Collections.singletonMap("access-token", token),
                HttpStatus.OK);
    }
    @PostMapping("/singin")
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        User newUser = UserMapper.toUser(userCreationDTO);
        Result<User, String> user = userService.createUser(newUser);

        if (user instanceof Success<User, String>) {
            UserDTO newUserDTO = UserMapper.toUserDTO(user.getValue());
            return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
