package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.GroupDTO;
import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.repositories.GroupRepo;
import com.example.YourPeopleBE.repositories.GroupReqRepo;
import com.example.YourPeopleBE.service.IGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements IGroupService {

    final GroupRepo groupRepo;
    final GroupReqRepo groupReqRepo;


    public GroupServiceImpl(GroupRepo groupRepo, GroupReqRepo groupReqRepo) {
        this.groupRepo = groupRepo;
        this.groupReqRepo = groupReqRepo;
    }

    @Override
    public Group createGroup(GroupDTO groupDTO) {
        Optional<Group> existingName =  groupRepo.findFirstByName(groupDTO.getName());
        if (existingName.isPresent()){
            return null;
        }

        Group newGroup = new Group();
        newGroup.setName(groupDTO.getName());
        newGroup.setDescription(groupDTO.getDescription());
        newGroup.setGroupAdmin(groupDTO.getGroupAdmin());
        newGroup.setSuspended(false);

        newGroup = groupRepo.save(newGroup);

        return newGroup;
    }

    @Override
    public Group findGroupById(Long groupId) {
        Optional<Group> group = groupRepo.findById(groupId);
        if (group.isPresent()){
            return group.get();
        }
        return null;
    }

    @Override
    public ERequestState checkGroupReq(User user, Group group) {
        Optional<GroupReq> groupReq = groupReqRepo.findFirstByFromAndGroup(user, group);
        if(groupReq.isPresent()){
            return groupReq.get().getApproved();
        }
        return null;
    }

    @Override
    public GroupReq sendGroupReq(User user, Group group) {
        ERequestState state = checkGroupReq(user, group);
        if(state != null){
            return null;
        }
        GroupReq newGroupReq = new GroupReq();
        newGroupReq.setGroup(group);
        newGroupReq.setFrom(user);
        newGroupReq.setApproved(ERequestState.WAITING);
        newGroupReq = groupReqRepo.save(newGroupReq);
        return newGroupReq;
    }

    @Override
    public GroupReq findGroupReqById(Long id) {
        Optional<GroupReq> groupReq = groupReqRepo.findById(id);
        if(groupReq.isEmpty()){
            return null;
        }
        return groupReq.get();
    }

    @Override
    public GroupReq updateGroupReq(GroupReq groupReq) {
        return groupReqRepo.save(groupReq);
    }

    @Override
    public List<Group> joinedGroups(User user) {
        //List<GroupReq> approvedGroups = groupReqRepo.findAllByFromAndApproved_Accepted(user);
        List<GroupReq> approvedGroups = groupReqRepo.findAllByFromAndApproved(user, ERequestState.ACCEPTED);
        List<Group> yourGroups = new ArrayList<>();
        for (GroupReq groupReq: approvedGroups) {
            yourGroups.add(groupReq.getGroup());
        }
        return yourGroups;
    }

    @Override
    public List<Group> groupsByYou(User user) {
        List<Group> madebyyou = groupRepo.findAllByGroupAdminAndSuspendedIsFalse(user);
        return madebyyou;
    }

    @Override
    public List<Group> availableGroups() {
        return groupRepo.findAllBySuspendedIsFalse();
    }

    @Override
    public List<Group> yourAvailableGroups(User user) {
        List<Group> availableForYou = groupRepo.findAllBySuspendedIsFalse();
        List<Group> yourGroups = joinedGroups(user);
        availableForYou.removeAll(yourGroups);
        return availableForYou;
    }

    @Override
    public List<GroupReq> waitingReqs(Group group) {
        return groupReqRepo.findAllByGroupAndApproved(group, ERequestState.WAITING);
    }

    @Override
    public List<GroupReq> acceptedReqs(Group group) {
        return groupReqRepo.findAllByGroupAndApproved(group, ERequestState.ACCEPTED);
    }

}
