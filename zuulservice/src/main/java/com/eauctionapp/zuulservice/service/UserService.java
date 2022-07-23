package com.eauctionapp.zuulservice.service;

import com.eauctionapp.zuulservice.entity.User;
import com.eauctionapp.zuulservice.model.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User save(UserDto user);

    UserDetails loadUserByUsername(String userId) throws
            UsernameNotFoundException;
}
