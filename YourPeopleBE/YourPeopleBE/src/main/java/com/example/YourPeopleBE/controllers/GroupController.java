package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.service.IGroupService;
import com.example.YourPeopleBE.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GroupController {

    final IGroupService groupService;
    final IUserService userService;
    final ModelMapper modelMapper;

    public GroupController(IGroupService groupService, IUserService userService, ModelMapper modelMapper) {
        this.groupService = groupService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
}
