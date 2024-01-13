package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.FriendReqDTO;
import com.example.YourPeopleBE.model.entity.FriendReq;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.service.IFriendsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService implements IFriendsService {
    @Override
    public FriendReq sendFriendReq(FriendReqDTO newFriendReq) {
        return null;
    }

    @Override
    public List<FriendReq> waitingRequestForYou(User user) {
        return null;
    }

    @Override
    public List<FriendReq> rejectedRequests(User user) {
        return null;
    }
}
