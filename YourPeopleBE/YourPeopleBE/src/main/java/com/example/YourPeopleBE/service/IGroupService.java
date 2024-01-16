package com.example.YourPeopleBE.service;

import com.example.YourPeopleBE.model.dto.GroupDTO;
import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;

import java.util.List;

public interface IGroupService {

    Group createGroup (GroupDTO groupDTO);
    Group findGroupById (Long groupId);

    GroupReq sendGroupReq (User user, Group group);
    List<Group> joinedGroups(User user);
    List<Group> groupsByYou(User user);
    List<Group> availableGroups();
    List<Group> yourAvailableGroups(User user);

    List<GroupReq> waitingReqs(Group group);

    ERequestState checkGroupReq(User user, Group group);
}
