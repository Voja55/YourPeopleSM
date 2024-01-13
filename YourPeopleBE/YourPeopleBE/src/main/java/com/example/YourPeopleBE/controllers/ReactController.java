package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.service.ICommentService;
import com.example.YourPeopleBE.service.IPostService;
import com.example.YourPeopleBE.service.IReactionService;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ReactController {

    final IUserService userService;
    final IReactionService reactionService;
    final IPostService postService;
    final ICommentService commentService;

    public ReactController(IUserService userService, IReactionService reactionService, IPostService postService, ICommentService commentService) {
        this.userService = userService;
        this.reactionService = reactionService;
        this.postService = postService;
        this.commentService = commentService;
    }
}
