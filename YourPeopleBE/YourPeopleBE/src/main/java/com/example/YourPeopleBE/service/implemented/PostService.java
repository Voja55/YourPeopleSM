package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.PostDTO;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.repositories.PostRepo;
import com.example.YourPeopleBE.service.IGroupService;
import com.example.YourPeopleBE.service.IPostService;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    final PostRepo postRepo;
    final IGroupService groupService;
    final IUserService userService;

    @Autowired
    public PostService(PostRepo postRepo, IGroupService groupService, IUserService userService) {
        this.postRepo = postRepo;
        this.groupService = groupService;
        this.userService = userService;
    }

    @Override
    public Post creastePost(PostDTO postDTO) {
        Post newPost = new Post();
        newPost.setContent(postDTO.getContent());
        newPost.setPostedBy(postDTO.getPostedBy());
        newPost.setPostedIn(postDTO.getPostedIn());
        newPost = postRepo.save(newPost);
        return newPost;
    }

    @Override
    public List<Post> findPostsByGroup(Long groupId) {
        Group group = groupService.findGroupById(groupId);
        List<Post> posts = postRepo.findAllByPostedIn(group);
        return posts;
    }

    @Override
    public List<Post> findPostsByUser(Long userId) {
        User user = userService.findByid(userId);
        List<Post> posts = postRepo.findAllByPostedBy(user);
        return posts;
    }

    @Override
    public Post findPostById(Long postId) {
        Optional<Post> post = postRepo.findById(postId);
        if (post.isPresent()){
            return post.get();
        }
        return null;
    }
}
