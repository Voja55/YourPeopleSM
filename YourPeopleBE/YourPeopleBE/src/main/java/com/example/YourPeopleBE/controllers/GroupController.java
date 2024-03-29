package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.model.dto.GroupDTO;
import com.example.YourPeopleBE.model.dto.GroupReqDTO;
import com.example.YourPeopleBE.model.dto.UserDTO;
import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.model.frontendDTO.GroupFEDTO;
import com.example.YourPeopleBE.model.frontendDTO.GroupReqFEDTO;
import com.example.YourPeopleBE.service.IGroupService;
import com.example.YourPeopleBE.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.DescriptorKey;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("group")
public class GroupController {

    final IGroupService groupService;
    final IUserService userService;
    final ModelMapper modelMapper;

    public GroupController(IGroupService groupService, IUserService userService, ModelMapper modelMapper) {
        this.groupService = groupService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody @Validated GroupDTO newGroup, Principal userinfo) {

//        Group createdGroup = groupService.createGroup(newGroup);
//        if (createdGroup == null) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
//        }
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        newGroup.setGroupAdmin(user);

        GroupDTO groupDTO = new GroupDTO(groupService.createGroup(newGroup));

        return new ResponseEntity(groupDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupFEDTO> group(@PathVariable(value = "groupId") Long id, Principal userinfo){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Group group = groupService.findGroupById(id);
        if (group == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        GroupFEDTO groupResponse = new GroupFEDTO();
        groupResponse.setId(group.getId());
        groupResponse.setName(group.getName());
        groupResponse.setDescription(group.getDescription());
        groupResponse.setCreationDate(group.getCreationDate());
        groupResponse.setGroupAdmin(group.getGroupAdmin().getUsername());
        groupResponse.setJoined(groupService.isJoinedGroup(user, group));
        return new ResponseEntity(groupResponse, HttpStatus.ACCEPTED);

    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<GroupFEDTO> groups(){
        List<Group> groups = groupService.availableGroups();
        List<GroupFEDTO> groupFEDTOList = new ArrayList<>();
        for (Group group:groups) {
            GroupFEDTO groupCopy = new GroupFEDTO();
            groupCopy.setId(group.getId());
            groupCopy.setName(group.getName());
            groupCopy.setDescription(group.getDescription());
            groupCopy.setCreationDate(group.getCreationDate());
            groupCopy.setGroupAdmin(group.getGroupAdmin().getUsername());
            groupFEDTOList.add(groupCopy);
        }
        return groupFEDTOList;
    }

    @GetMapping("/foryou")
    public List<GroupFEDTO> groupsforyou(Principal userinfo){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return null;
        }
        List<Group> groups = groupService.yourAvailableGroups(user);
        List<GroupFEDTO> groupFEDTOList = new ArrayList<>();
        for (Group group:groups) {
            GroupFEDTO groupCopy = new GroupFEDTO();
            groupCopy.setId(group.getId());
            groupCopy.setName(group.getName());
            groupCopy.setDescription(group.getDescription());
            groupCopy.setCreationDate(group.getCreationDate());
            groupCopy.setGroupAdmin(group.getGroupAdmin().getUsername());
            groupFEDTOList.add(groupCopy);
        }
        return groupFEDTOList;
    }

    @GetMapping("/yourGroups")
    public List<GroupFEDTO> yourGroups(Principal userinfo){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return null;
        }
        List<Group> groups = groupService.groupsByYou(user);
        List<GroupFEDTO> groupFEDTOList = new ArrayList<>();
        for (Group group:groups) {
            GroupFEDTO groupCopy = new GroupFEDTO();
            groupCopy.setId(group.getId());
            groupCopy.setName(group.getName());
            groupCopy.setDescription(group.getDescription());
            groupCopy.setCreationDate(group.getCreationDate());
            groupCopy.setGroupAdmin(group.getGroupAdmin().getUsername());
            groupFEDTOList.add(groupCopy);
        }
        return groupFEDTOList;
    }

    @GetMapping("/groupsBy/{username}")
    public List<GroupFEDTO> userGroups(@PathVariable(value = "username")String username){
        User user = userService.findByUsername(username);
        if (user == null) {
            return null;
        }
        List<Group> groups = groupService.groupsByYou(user);
        List<GroupFEDTO> groupFEDTOList = new ArrayList<>();
        for (Group group:groups) {
            GroupFEDTO groupCopy = new GroupFEDTO();
            groupCopy.setId(group.getId());
            groupCopy.setName(group.getName());
            groupCopy.setDescription(group.getDescription());
            groupCopy.setCreationDate(group.getCreationDate());
            groupCopy.setGroupAdmin(group.getGroupAdmin().getUsername());
            groupFEDTOList.add(groupCopy);
        }
        return groupFEDTOList;
    }

    @GetMapping("/joinedGroups")
    public List<GroupFEDTO> joinedGroups(Principal userinfo){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return null;
        }
        List<Group> groups = groupService.joinedGroups(user);
        List<GroupFEDTO> groupFEDTOList = new ArrayList<>();
        for (Group group:groups) {
            GroupFEDTO groupCopy = new GroupFEDTO();
            groupCopy.setId(group.getId());
            groupCopy.setName(group.getName());
            groupCopy.setDescription(group.getDescription());
            groupCopy.setCreationDate(group.getCreationDate());
            groupCopy.setGroupAdmin(group.getGroupAdmin().getUsername());
            groupFEDTOList.add(groupCopy);
        }
        return groupFEDTOList;
    }
    @GetMapping("/userMemberOf/{username}")
    public List<GroupFEDTO> userJoinedGroups(@PathVariable(value = "username")String username){
        User user = userService.findByUsername(username);
        if (user == null) {
            return null;
        }
        List<Group> groups = groupService.joinedGroups(user);
        List<GroupFEDTO> groupFEDTOList = new ArrayList<>();
        for (Group group:groups) {
            GroupFEDTO groupCopy = new GroupFEDTO();
            groupCopy.setId(group.getId());
            groupCopy.setName(group.getName());
            groupCopy.setDescription(group.getDescription());
            groupCopy.setCreationDate(group.getCreationDate());
            groupCopy.setGroupAdmin(group.getGroupAdmin().getUsername());
            groupFEDTOList.add(groupCopy);
        }
        return groupFEDTOList;
    }

    @GetMapping("/{groupId}/members")
    public List<UserDTO> groupMembers(@PathVariable(value = "groupId") Long id){
        Group group = groupService.findGroupById(id);
        if (group == null){
            return null;
        }
        List<GroupReq> accpeted = groupService.acceptedReqs(group);
        List<UserDTO> users = new ArrayList<>();
        for(GroupReq req : accpeted){
            users.add(modelMapper.map(req.getFrom(), UserDTO.class));
        }
        return users;
    }

    //=====================================GROUP REQUEST=======================================================

    @PostMapping("/request{groupId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GroupReq> createGroupReq(@PathVariable(value = "groupId") Long id, Principal userinfo) {

        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Group group = groupService.findGroupById(id);
        if (group == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        GroupReqDTO groupReqDTO =new GroupReqDTO(groupService.sendGroupReq(user, group));

        return new ResponseEntity(groupReqDTO, HttpStatus.CREATED);
    }

    @GetMapping("/requests/waiting")
    public List<GroupReqFEDTO> reqsForYou(Principal userinfo){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return null;
        }
        List<Group> groups = groupService.findGroupsByAdmin(user);
        List<GroupReqFEDTO> groupReqsResponse = new ArrayList<>();
        for (Group group: groups){
            List<GroupReq> groupReqs = groupService.waitingReqs(group);
            for (GroupReq groupReq: groupReqs){
                GroupReqFEDTO groupReqFEDTO = new GroupReqFEDTO();
                groupReqFEDTO.setId(groupReq.getId());
                groupReqFEDTO.setApproved(groupReq.getApproved());
                groupReqFEDTO.setCreationTime(groupReq.getCreationTime());
                groupReqFEDTO.setAnswerTime(groupReq.getAnswerTime());
                groupReqFEDTO.setFrom(groupReq.getFrom().getUsername());
                groupReqFEDTO.setGroupName(groupReq.getGroup().getName());
                groupReqFEDTO.setGroupId(groupReq.getGroup().getId());
                groupReqsResponse.add(groupReqFEDTO);
            }
        }
        return groupReqsResponse;
    }

    @CrossOrigin
    @PutMapping("/request/accept{reqId}")
    public ResponseEntity<GroupReqDTO> acceptReq(@PathVariable(value = "reqId") Long id, Principal userinfo){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        GroupReq proccessedGroupReq = groupService.findGroupReqById(id);
        proccessedGroupReq.setApproved(ERequestState.ACCEPTED);
        GroupReqDTO groupReqDTO = new GroupReqDTO(groupService.updateGroupReq(proccessedGroupReq));

        return new ResponseEntity(groupReqDTO, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/request/reject{reqId}")
    public ResponseEntity<GroupReqDTO> rejectReq(Principal userInfo, @PathVariable(value = "reqId") Long id){
        User user = userService.findByUsername(userInfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        GroupReq proccessedGroupReq = groupService.findGroupReqById(id);
        proccessedGroupReq.setApproved(ERequestState.REJECTED);
        GroupReqDTO groupReqDTO = new GroupReqDTO(groupService.updateGroupReq(proccessedGroupReq));

        return new ResponseEntity(groupReqDTO, HttpStatus.CREATED);
    }

    @PostMapping("/suspend{id}")
    public void suspendGroup(@PathVariable(value = "id") Long id, @RequestBody String reason){
        groupService.suspend(id,reason);
    }
}
