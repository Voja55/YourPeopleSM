package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.CommentDTO;
import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.repositories.CommentRepo;
import com.example.YourPeopleBE.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    final CommentRepo commentRepo;

    final PostService postService;

    @Autowired
    public CommentService(CommentRepo commentRepo, PostService postService) {
        this.commentRepo = commentRepo;
        this.postService = postService;
    }

    @Override
    public Comment createComment(CommentDTO commentDTO) {
        Comment newCom = new Comment();
        newCom.setText(commentDTO.getText());
        newCom.setPostedBy(commentDTO.getPostedBy());
        newCom.setCommentedOn(commentDTO.getCommentedOn());
        newCom.setRepliesTo(commentDTO.getRepliesTo());
        newCom = commentRepo.save(newCom);
        return newCom;
    }

    @Override
    public Comment findCommentById(Long id) {
        Optional<Comment> comment = commentRepo.findById(id);
        if (comment.isEmpty()){
            return null;
        }
        return comment.get();
    }

    @Override
    public List<Comment> findCommentsByPost(Long postId) {
        Post post = postService.findPostById(postId);
        List<Comment> comments = commentRepo.findAllByCommentedOn(post);
        return comments;
    }

    @Override
    public List<Comment> findCommentsByComment(Long commId) {
        Comment comment = findCommentById(commId);
        List<Comment> comments = commentRepo.findAllByRepliesTo(comment);
        return comments;
    }
}
