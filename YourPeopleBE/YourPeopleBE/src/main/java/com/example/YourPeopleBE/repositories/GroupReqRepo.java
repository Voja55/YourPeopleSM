package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupReqRepo extends JpaRepository<GroupReq, Long> {
    public List<GroupReq> findAllByGroupAndApproved_Waiting(Group group);

    public List<GroupReq> findAllByFromAndApproved_Accepted(User user);
    public List<GroupReq> findAllByGroupAndApproved_Accepted(Group group);
    public Optional<GroupReq> findFirstByFromAndGroup(User user, Group group);
}
