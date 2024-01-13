package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.service.ICommentService;
import com.example.YourPeopleBE.service.IPostService;
import com.example.YourPeopleBE.service.IReactionService;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CommentController {
    final ICommentService commentService;
    final IPostService postService;
    final IReactionService reactionService;
    final IUserService userService;

    public CommentController(ICommentService commentService, IPostService postService, IReactionService reactionService, IUserService userService) {
        this.commentService = commentService;
        this.postService = postService;
        this.reactionService = reactionService;
        this.userService = userService;
    }
}
