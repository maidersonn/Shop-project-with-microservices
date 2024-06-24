package com.maider.auth.domain.services;

import com.maider.auth.domain.entities.User;
import com.maider.auth.domain.repositories.UserRepository;
import com.maider.auth.config.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            return UserDetailsImpl.build(user);
        } catch (Exception e){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}
