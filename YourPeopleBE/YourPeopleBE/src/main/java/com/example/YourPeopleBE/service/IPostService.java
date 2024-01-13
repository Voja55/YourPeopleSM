package com.example.YourPeopleBE.service;

import com.example.YourPeopleBE.model.dto.PostDTO;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.Post;

import java.util.List;

public interface IPostService {

    Post creastePost(PostDTO postDTO);
    List<Post> findPostsByGroup(Long groupId);
    List<Post> findPostsByUser(Long userId);
    Post findPostById(Long postId);

}
