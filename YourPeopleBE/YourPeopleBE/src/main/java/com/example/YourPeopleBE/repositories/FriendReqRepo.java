package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.FriendReq;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendReqRepo extends JpaRepository<FriendReq, Long> {
    public List<GroupReq> findAllByTo (User user);

    public List<GroupReq> findAllByApproved_AcceptedAndTo (User user);
}
