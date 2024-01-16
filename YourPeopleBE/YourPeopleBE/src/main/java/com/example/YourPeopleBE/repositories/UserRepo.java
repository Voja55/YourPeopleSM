package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findFirstByUsername(String username);
    public List<User> findAllByNameContainsOrSurnameContains(String search1, String search2);
}
