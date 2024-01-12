package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepo extends JpaRepository<Reaction, Long> {

    public List<Reaction> findAllByReactedOnComment(Comment comment);
    public List<Reaction> findAllByReactedOnPost(Post post);
}
