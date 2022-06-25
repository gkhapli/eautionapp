package com.authenticationservice.controller;

import com.authenticationservice.model.User;
import com.authenticationservice.model.UserDto;
import com.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

}
