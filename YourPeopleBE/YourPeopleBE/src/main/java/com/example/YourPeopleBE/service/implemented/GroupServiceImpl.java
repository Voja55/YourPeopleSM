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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        GroupReq newGroupReq = new GroupReq();
        newGroupReq.setGroup(newGroup);
        newGroupReq.setFrom(newGroup.getGroupAdmin());
        newGroupReq.setApproved(ERequestState.ACCEPTED);
        groupReqRepo.save(newGroupReq);

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
        List<GroupReq> groupReqs = groupReqRepo.findAllByFromAndGroup(user, group);
        for (GroupReq groupReq: groupReqs){
            if (groupReq.getApproved().equals(ERequestState.ACCEPTED)){
                return ERequestState.ACCEPTED;
            }
        }
        for (GroupReq groupReq: groupReqs){
            if (groupReq.getApproved().equals(ERequestState.WAITING)){
                return ERequestState.WAITING;
            }
        }
        return ERequestState.REJECTED;

//        Optional<GroupReq> groupReq = groupReqRepo.findFirstByFromAndGroup(user, group);
//        if(groupReq.isPresent()){
//            return groupReq.get().getApproved();
//        }
//        return null;
    }

    @Override
    public boolean isJoinedGroup(User user, Group group) {

        List<GroupReq> groupReqs = groupReqRepo.findAllByFromAndGroup(user, group);
        for (GroupReq groupReq: groupReqs){
            if(groupReq.getApproved().equals(ERequestState.ACCEPTED)){
                return true;
            }
        }
        return false;

//        Optional<GroupReq> groupReq1 = groupReqRepo.findFirstByFromAndGroup(user, group);
//        if (groupReq1.isPresent()){
//            if (groupReq1.get().getApproved().equals(ERequestState.ACCEPTED)){
//                return true;
//            }
//            return false;
//        }
//        return false;
    }

    @Override
    public void suspend(Long id, String reason) {
        Optional<Group> group = groupRepo.findById(id);
        if (group.isPresent()){
            Group groupsus = group.get();
            groupsus.setSuspended(true);
            groupsus.setSuspendedReason(reason);
            groupsus.setSuspendedTime(LocalDateTime.now());
            groupRepo.save(groupsus);
        }
    }

    @Override
    public List<Group> findGroupsByAdmin(User user) {
        return groupRepo.findAllByGroupAdminAndSuspendedIsFalse(user);
    }

    @Override
    public GroupReq sendGroupReq(User user, Group group) {
        ERequestState state = checkGroupReq(user, group);
        if(state.equals(ERequestState.REJECTED)) {
            GroupReq newGroupReq = new GroupReq();
            newGroupReq.setGroup(group);
            newGroupReq.setFrom(user);
            newGroupReq.setApproved(ERequestState.WAITING);
            System.out.println(newGroupReq);
            GroupReq responsereq = groupReqRepo.save(newGroupReq);
            return responsereq;
        }
        return null;
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
            if(!groupReq.getGroup().isSuspended()){
                yourGroups.add(groupReq.getGroup());
            }

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
        List<Group> finallist = availableForYou.stream().filter(group -> !yourGroups.stream()
                .anyMatch(g -> g.getId().equals(group.getId()))).collect(Collectors.toList());
        System.out.println(finallist);
        return finallist;
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
