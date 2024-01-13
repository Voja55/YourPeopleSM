package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByCommentedOn (Post post);
    public List<Comment> findAllByRepliesTo (Comment comment);
}
