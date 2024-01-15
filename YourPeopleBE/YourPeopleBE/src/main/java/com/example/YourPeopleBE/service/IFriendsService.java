package com.example.YourPeopleBE.service;

import com.example.YourPeopleBE.model.dto.FriendReqDTO;
import com.example.YourPeopleBE.model.entity.FriendReq;
import com.example.YourPeopleBE.model.entity.User;

import java.util.List;

public interface IFriendsService {

    List<User> yourFriends(User user);
    FriendReq sendFriendReq(FriendReqDTO newFriendReq);
    List<FriendReq> waitingRequestForYou(User user);
    List<FriendReq> rejectedRequests(User user);
}
