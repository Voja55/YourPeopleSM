package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.service.IGroupService;
import com.example.YourPeopleBE.service.IPostService;
import com.example.YourPeopleBE.service.IReactionService;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PostController {

    final IPostService postService;

    final IUserService userService;

    final IGroupService groupService;

    final IReactionService reactionService;

    public PostController(IPostService postService, IUserService userService, IGroupService groupService, IReactionService reactionService) {
        this.postService = postService;
        this.userService = userService;
        this.groupService = groupService;
        this.reactionService = reactionService;
    }
}
