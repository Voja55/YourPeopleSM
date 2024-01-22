package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.Reaction;
import com.example.YourPeopleBE.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepo extends JpaRepository<Reaction, Long> {

    public Optional<Reaction> findFirstByReactedByAndAndReactedOnComment(User user, Comment comment);
    public Optional<Reaction> findFirstByReactedByAndAndReactedOnPost(User user, Post post);
    public List<Reaction> findAllByReactedOnComment(Comment comment);
    public List<Reaction> findAllByReactedOnPost(Post post);
}
