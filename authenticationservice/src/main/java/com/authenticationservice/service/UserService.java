package com.authenticationservice.service;

import com.authenticationservice.model.UserDto;
import com.authenticationservice.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User save(UserDto user);

    UserDetails loadUserByUsername(String userId) throws
            UsernameNotFoundException;
}
