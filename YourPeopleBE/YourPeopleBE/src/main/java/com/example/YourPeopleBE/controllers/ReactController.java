package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.model.dto.ReactionDTO;
import com.example.YourPeopleBE.model.dto.ReactionTypeDTO;
import com.example.YourPeopleBE.model.entity.*;
import com.example.YourPeopleBE.service.ICommentService;
import com.example.YourPeopleBE.service.IPostService;
import com.example.YourPeopleBE.service.IReactionService;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @PostMapping("/react/post/{postID}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReactionDTO> reactPost(@RequestBody ReactionTypeDTO reactiontype, @PathVariable(value = "postID") Long postId, Principal userinfo){

        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Post post = postService.findPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        ReactionDTO reactDTO = new ReactionDTO();
        reactDTO.setReactionType(EReactionType.valueOf(reactiontype.getReaction()));
        reactDTO.setReactedBy(user);
        reactDTO.setReactedOnPost(post);

        Reaction newReact = reactionService.createReaction(reactDTO, "post");
        if (newReact == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        ReactionDTO newReactDTO = new ReactionDTO(newReact);

        return new ResponseEntity(newReactDTO, HttpStatus.OK);
    }

    @PostMapping("/react/comment/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReactionDTO> reactComment(@RequestBody ReactionTypeDTO reactiontype, @PathVariable(value = "commentId") Long commentId, Principal userinfo){

        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Comment comment = commentService.findCommentById(commentId);
        if (comment == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        ReactionDTO reactDTO = new ReactionDTO();
        reactDTO.setReactionType(EReactionType.valueOf(reactiontype.getReaction()));
        reactDTO.setReactedBy(user);
        reactDTO.setReactedOnComment(comment);

        Reaction newReact = reactionService.createReaction(reactDTO, "comment");
        if (newReact == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        ReactionDTO newReactDTO = new ReactionDTO(newReact);

        return new ResponseEntity(newReactDTO, HttpStatus.OK);
    }
}
