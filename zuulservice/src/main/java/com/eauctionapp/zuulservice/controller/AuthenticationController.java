package com.eauctionapp.zuulservice.controller;

import com.eauctionapp.zuulservice.model.AuthToken;
import com.eauctionapp.zuulservice.model.UserDto;
import com.eauctionapp.zuulservice.service.UserService;
import com.eauctionapp.zuulservice.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody UserDto userDto)
            throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails user = userService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthToken(token));
    }
}
