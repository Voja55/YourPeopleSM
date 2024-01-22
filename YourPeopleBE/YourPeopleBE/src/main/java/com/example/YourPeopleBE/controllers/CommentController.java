package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.model.dto.CommentDTO;
import com.example.YourPeopleBE.model.dto.PostStatDTO;
import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.Reaction;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.model.frontendDTO.CommentFEDTO;
import com.example.YourPeopleBE.service.ICommentService;
import com.example.YourPeopleBE.service.IPostService;
import com.example.YourPeopleBE.service.IReactionService;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/create/onPost/{postId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDTO> createCommentPost(@RequestBody @Validated CommentDTO newCom,
                                                        @PathVariable(value = "postId") Long postId, Principal userinfo) {

        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Post post = postService.findPostById(postId);
        if (post == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        newCom.setCommentedOn(post);
        newCom.setPostedBy(user);
        Comment createdCom = commentService.createComment(newCom);
        if (createdCom == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        CommentDTO commentDTO = new CommentDTO(createdCom);

        return new ResponseEntity(commentDTO, HttpStatus.OK);
    }

    @PostMapping("/create/onCom/{comId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDTO> createCommentCom(@RequestBody @Validated CommentDTO newCom,
                                                        @PathVariable(value = "comId") Long comId, Principal userinfo) {

        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Comment comment = commentService.findCommentById(comId);
        if (comment == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        newCom.setRepliesTo(comment);
        newCom.setPostedBy(user);
        Comment createdCom = commentService.createComment(newCom);
        if (createdCom == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        CommentDTO commentDTO = new CommentDTO(createdCom);

        return new ResponseEntity(commentDTO, HttpStatus.OK);
    }

    @GetMapping("/commentStats/{commentId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Integer commentStats(@PathVariable(value = "commentId") Long commentId){
        List<Reaction> reactions =reactionService.findReactionsOnCom(commentId);
        return reactions.size();
    }

    @GetMapping("/comments/{postId}")
    public List<CommentFEDTO> commentsOfPost (@PathVariable(value = "postId") Long postId){
        List<Comment> comments = commentService.findCommentsByPost(postId);
        List<CommentFEDTO> commentsFE = new ArrayList<>();
        for (Comment comment: comments){
            CommentFEDTO commentFEDTO = new CommentFEDTO(comment);
            commentFEDTO.setReactions(reactionService.findReactionsOnCom(comment.getId()).size());
            commentsFE.add(commentFEDTO);
        }
        return commentsFE;
    }

    @GetMapping("/replies/{commentId}")
    public List<CommentFEDTO> repliesOfCom (@PathVariable(value = "commentId") Long commentId){
        List<Comment> comments = commentService.findCommentsByComment(commentId);
        List<CommentFEDTO> commentsFE = new ArrayList<>();
        for (Comment comment: comments){
            CommentFEDTO commentFEDTO = new CommentFEDTO(comment, "reda radi da bi znao da je replie a ne comment");
            commentFEDTO.setReactions(reactionService.findReactionsOnCom(comment.getId()).size());
            commentsFE.add(commentFEDTO);
        }
        return commentsFE;
    }
}
