package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {

    public Optional<Group> findFirstByName (String name);

    public List<Group> findAllByNameContains(String search);

    public List<Group> findAllBySuspendedIsFalse();
}
