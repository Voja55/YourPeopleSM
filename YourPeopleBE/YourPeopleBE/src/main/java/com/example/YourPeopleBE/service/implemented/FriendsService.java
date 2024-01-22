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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FriendsService implements IFriendsService {

    final UserRepo userRepo;

    final FriendReqRepo friendReqRepo;


    public FriendsService(UserRepo userRepo, FriendReqRepo friendReqRepo) {
        this.userRepo = userRepo;
        this.friendReqRepo = friendReqRepo;
    }

    @Override
    public List<User> yourFriends(User user) {
        List<User> allusers = new ArrayList<>();
        List<FriendReq> friendsyousent = friendReqRepo.findAllByApprovedAndFrom(ERequestState.ACCEPTED,user);
        for (FriendReq friendReq : friendsyousent){
            allusers.add(friendReq.getTo());
        }
        List<FriendReq> friendsyourecived = friendReqRepo.findAllByApprovedAndTo(ERequestState.ACCEPTED,user);
        for (FriendReq friendReq: friendsyourecived){
            allusers.add(friendReq.getFrom());
        }
        return allusers;
    }

    @Override
    public boolean alreadyFriends(User you, User user) {
        List<FriendReq> friendReqs1 = friendReqRepo.findAllByFromAndTo(you, user);
        for (FriendReq friendReq : friendReqs1){
            if (friendReq.getApproved().equals(ERequestState.ACCEPTED)){
                return true;
            }
        }
        List<FriendReq> friendReqs2 = friendReqRepo.findAllByFromAndTo(user, you);
        for (FriendReq friendReq : friendReqs2){
            if (friendReq.getApproved().equals(ERequestState.ACCEPTED)){
                return true;
            }
        }
        return false;
    }

    @Override
    public FriendReq sendFriendReq(User sender, User reciver) {
        FriendReq friendReq = findFriendReqByFromAndTo(sender, reciver);
        if (friendReq == null){
            FriendReq newFriendReq = new FriendReq();
            newFriendReq.setTo(reciver);
            newFriendReq.setFrom(sender);
            newFriendReq.setApproved(ERequestState.WAITING);
            newFriendReq = friendReqRepo.save(newFriendReq);
            return newFriendReq;
        } else if (friendReq.getApproved().equals(ERequestState.WAITING)) {
            return null;
        }else if (friendReq.getApproved().equals(ERequestState.REJECTED)) {
            FriendReq newFriendReq = new FriendReq();
            newFriendReq.setTo(reciver);
            newFriendReq.setFrom(sender);
            newFriendReq.setApproved(ERequestState.WAITING);
            newFriendReq = friendReqRepo.save(newFriendReq);
            return newFriendReq;
        }
        return null;
    }

    @Override
    public List<FriendReq> waitingRequestForYou(User user) {
        return friendReqRepo.findAllByApprovedAndTo(ERequestState.WAITING,user);
    }

    @Override
    public List<FriendReq> rejectedRequests(User user) {
        return friendReqRepo.findAllByApprovedAndFrom(ERequestState.REJECTED, user);
    }

    @Override
    public FriendReq findFriendReqById(Long id) {
        Optional<FriendReq> friendReq = friendReqRepo.findById(id);
        if (friendReq.isPresent()){
            return friendReq.get();
        }
        return null;
    }

    @Override
    public FriendReq findFriendReqByFromAndTo(User user1, User user2) {
        System.out.println(user1.getUsername());
        System.out.println(user2.getUsername());
        Optional<FriendReq> friendReq1 = friendReqRepo.findFirstByFromAndTo(user1, user2);
        Optional<FriendReq> friendReq2 = friendReqRepo.findFirstByFromAndTo(user2, user1);
        if (friendReq1.isPresent()){
            return friendReq1.get();
        }
        if (friendReq2.isPresent()){
            return friendReq2.get();
        }
        return null;
    }

    @Override
    public FriendReq updateFriendReq(FriendReq proccessedFR) {
        return friendReqRepo.save(proccessedFR);
    }


}
