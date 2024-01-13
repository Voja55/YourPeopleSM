package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupReqRepo extends JpaRepository<GroupReq, Long> {
    public List<GroupReq> findAllByGroupAndApproved_Waiting(Group group);

    public List<GroupReq> findAllByGroupAndApproved_Accepted(Group group);
}
