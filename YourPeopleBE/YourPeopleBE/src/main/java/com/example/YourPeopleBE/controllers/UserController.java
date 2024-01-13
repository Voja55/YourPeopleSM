package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.security.TokenUtils;
import com.example.YourPeopleBE.service.IFriendsService;
import com.example.YourPeopleBE.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    final IUserService userService;

    final IFriendsService friendsService;

    final PasswordEncoder passwordEncoder;

    final ModelMapper modelMapper;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    public UserController(IUserService userService, IFriendsService friendsService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userService = userService;
        this.friendsService = friendsService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }
}
