package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.CommentDTO;
import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Override
    public Comment createComment(CommentDTO newCom) {
        return null;
    }

    @Override
    public Comment findCommentById(Long id) {
        return null;
    }

    @Override
    public List<Comment> findCommentsByPost(Long postId) {
        return null;
    }

    @Override
    public List<Comment> findCommentsByComment(Long commId) {
        return null;
    }
}
