package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    public Optional<Post> findFirstById(Long id);
    public List<Post> findAllByPostedIn(Group group);
    public List<Post> findAllByPostedBy(User user);

}
