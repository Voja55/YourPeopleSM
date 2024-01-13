package com.example.YourPeopleBE.service;

import com.example.YourPeopleBE.model.dto.CommentDTO;
import com.example.YourPeopleBE.model.entity.Comment;

import java.util.List;

public interface ICommentService {

    Comment createComment(CommentDTO newCom);
    Comment findCommentById(Long id);
    List<Comment> findCommentsByPost(Long postId);
    List<Comment> findCommentsByComment(Long commId);
}
