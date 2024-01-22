package com.example.YourPeopleBE.service;

import com.example.YourPeopleBE.model.dto.FriendReqDTO;
import com.example.YourPeopleBE.model.entity.FriendReq;
import com.example.YourPeopleBE.model.entity.User;

import java.util.List;

public interface IFriendsService {

    List<User> yourFriends(User user);
    FriendReq sendFriendReq(User sender, User reciver);
    List<FriendReq> waitingRequestForYou(User user);
    List<FriendReq> rejectedRequests(User user);

    FriendReq findFriendReqById(Long id);
    FriendReq findFriendReqByFromAndTo(User from, User to);

    FriendReq updateFriendReq(FriendReq proccessedFR);

    boolean alreadyFriends(User you, User user);
}
