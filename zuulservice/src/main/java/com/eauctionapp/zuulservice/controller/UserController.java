package com.eauctionapp.zuulservice.controller;

import com.eauctionapp.zuulservice.entity.User;
import com.eauctionapp.zuulservice.model.UserDto;
import com.eauctionapp.zuulservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/signup")
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

}
