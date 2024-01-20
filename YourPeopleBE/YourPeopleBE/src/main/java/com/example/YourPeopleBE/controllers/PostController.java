package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.model.dto.PostDTO;
import com.example.YourPeopleBE.model.dto.PostStatDTO;
import com.example.YourPeopleBE.model.entity.*;
import com.example.YourPeopleBE.model.frontendDTO.PostFEDTO;
import com.example.YourPeopleBE.service.*;
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
public class PostController {

    final IPostService postService;

    final IUserService userService;

    final IGroupService groupService;

    final IReactionService reactionService;

    final ICommentService commentService;

    public PostController(IPostService postService, IUserService userService, IGroupService groupService, IReactionService reactionService, ICommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.groupService = groupService;
        this.reactionService = reactionService;
        this.commentService = commentService;
    }
    @PostMapping("/group/{groupID}/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostDTO> createPost(@RequestBody @Validated PostDTO newPost, @PathVariable(value = "groupID") Long groupId, Principal userinfo) {

        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Group group = groupService.findGroupById(groupId);
        if (group == null) {
           return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        boolean check = postService.checkposting(user, group);
        if (!check) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        newPost.setPostedgroup(group);
        newPost.setPostedBy(user);
        Post createdPost = postService.creastePost(newPost);
        if (createdPost == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PostDTO postDTO = new PostDTO(createdPost);

        return new ResponseEntity(postDTO, HttpStatus.CREATED);
    }

    @GetMapping("/postStat/{postId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public PostStatDTO postStats(@PathVariable(value = "postId") Long postId){
        List<Reaction> reactions =reactionService.findReactionsOnPost(postId);
        List<Comment> comments = commentService.findCommentsByPost(postId);
        PostStatDTO postStatDTO = new PostStatDTO();
        postStatDTO.setReactionsNum(reactions.size());
        postStatDTO.setCommentsNum(comments.size());
        return postStatDTO;
    }

    @GetMapping("/group/{groupId}/posts")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Post> communityPosts(@PathVariable(value = "groupId") Long id){
        return postService.findPostsByGroup(id);
    }

    @GetMapping("/userPosts")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PostFEDTO> userPosts(Principal userInfo){
        User user = userService.findByUsername(userInfo.getName());
        List<Post> posts = postService.findPostsByUser(user.getId());
        List<PostFEDTO> postsfe = new ArrayList<>();
        for (Post post: posts) {
            PostFEDTO newPost = new PostFEDTO();
            newPost.setId(post.getId());
            newPost.setUsername(post.getPostedBy().getUsername());
            newPost.setGroupName(post.getPostedgroup().getName());
            newPost.setGroupId(post.getPostedgroup().getId());
            newPost.setContent(post.getContent());
            newPost.setCreationDate(post.getCreationDate());
            List<Reaction> reactions =reactionService.findReactionsOnPost(post.getId());
            newPost.setReactionStat(reactions.size());

            postsfe.add(newPost);
        }
        return postsfe;
    }

    @GetMapping("/post/{postId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Post getPost(@PathVariable(value="postId") Long id){
        return postService.findPostById(id);
    }
}
