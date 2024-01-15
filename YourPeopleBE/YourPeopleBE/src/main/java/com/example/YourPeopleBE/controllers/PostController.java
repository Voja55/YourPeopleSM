package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.model.dto.PostDTO;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.service.IGroupService;
import com.example.YourPeopleBE.service.IPostService;
import com.example.YourPeopleBE.service.IReactionService;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    @PostMapping("/Group/{groupID}/create")
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

        newPost.setPostedIn(group);
        newPost.setPostedBy(user);
        Post createdPost = postService.creastePost(newPost);
        if (createdPost == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        PostDTO postDTO = new PostDTO(createdPost);

        return new ResponseEntity(postDTO, HttpStatus.CREATED);
    }

    @GetMapping("/group/{groupId}/posts")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Post> communityPosts(@PathVariable(value = "groupId") Long id){
        return postService.findPostsByGroup(id);
    }

    @GetMapping("/userPosts")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Post> userPosts(Principal userInfo){
        User user = userService.findByUsername(userInfo.getName());
        return postService.findPostsByUser(user.getId());
    }

    @GetMapping("/post/{postId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Post getPost(@PathVariable(value="postId") Long id){
        return postService.findPostById(id);
    }
}
