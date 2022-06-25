package com.authenticationservice.controller;

import com.authenticationservice.model.UserDto;
import com.authenticationservice.model.User;
import com.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }
    // Other methods
}
