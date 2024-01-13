package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.PostDTO;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {
    @Override
    public Post creastePost(PostDTO postDTO) {
        return null;
    }

    @Override
    public List<Post> findPostsByGroup(Long groupId) {
        return null;
    }

    @Override
    public List<Post> findPostsByUser(Long userId) {
        return null;
    }

    @Override
    public Post findPostById(Long postId) {
        return null;
    }
}
