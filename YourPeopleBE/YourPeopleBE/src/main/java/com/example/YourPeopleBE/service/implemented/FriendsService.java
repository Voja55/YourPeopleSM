package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.FriendReqDTO;
import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.FriendReq;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.repositories.FriendReqRepo;
import com.example.YourPeopleBE.repositories.UserRepo;
import com.example.YourPeopleBE.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService implements IFriendsService {

    final UserRepo userRepo;

    final FriendReqRepo friendReqRepo;

    @Autowired
    public FriendsService(UserRepo userRepo, FriendReqRepo friendReqRepo) {
        this.userRepo = userRepo;
        this.friendReqRepo = friendReqRepo;
    }

    @Override
    public List<User> yourFriends(User user) {
        List<User> allusers = userRepo.findAll();
        List<FriendReq> friendsyousent = friendReqRepo.findAllByApproved_AcceptedAndFrom(user);
        List<FriendReq> friendsyourecived = friendReqRepo.findAllByApproved_AcceptedAndTo(user);
        allusers.removeAll(friendsyousent);
        allusers.removeAll(friendsyourecived);

        return allusers;
    }

    @Override
    public FriendReq sendFriendReq(FriendReqDTO friendReqDTO) {
        User user = friendReqDTO.getFrom();
        if(yourFriends(user).isEmpty()){
            FriendReq newFriendReq = new FriendReq();
            newFriendReq.setTo(friendReqDTO.getTo());
            newFriendReq.setFrom(friendReqDTO.getFrom());
            newFriendReq.setApproved(ERequestState.WAITING);
            newFriendReq = friendReqRepo.save(newFriendReq);
            return newFriendReq;
        }
        return null;
    }

    @Override
    public List<FriendReq> waitingRequestForYou(User user) {
        return friendReqRepo.findAllByApproved_WaitingAndTo(user);
    }

    @Override
    public List<FriendReq> rejectedRequests(User user) {
        return friendReqRepo.findAllByApproved_RejectedAndFrom(user);
    }
}
