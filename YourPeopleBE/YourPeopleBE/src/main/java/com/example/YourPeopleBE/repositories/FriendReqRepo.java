package com.example.YourPeopleBE.repositories;

import com.example.YourPeopleBE.model.entity.FriendReq;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendReqRepo extends JpaRepository<FriendReq, Long> {
    public List<FriendReq> findAllByTo (User user);

    public List<FriendReq> findAllByApproved_AcceptedAndTo (User user);
    public List<FriendReq> findAllByApproved_WaitingAndTo (User user);
    public List<FriendReq> findAllByApproved_AcceptedAndFrom (User user);
    public List<FriendReq> findAllByApproved_RejectedAndFrom (User user);
}
