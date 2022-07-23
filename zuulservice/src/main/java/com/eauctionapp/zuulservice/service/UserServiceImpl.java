package com.eauctionapp.zuulservice.service;

import com.eauctionapp.zuulservice.entity.User;
import com.eauctionapp.zuulservice.model.UserDto;
import com.eauctionapp.zuulservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User save(UserDto user) {
        User newUser = new User(user.getUsername(),passwordEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }
    public UserDetails loadUserByUsername(String userId) throws
            UsernameNotFoundException {
        User user = userDao.findByUsername(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
