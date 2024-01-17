package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.model.dto.GroupDTO;
import com.example.YourPeopleBE.model.dto.GroupReqDTO;
import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.service.IGroupService;
import com.example.YourPeopleBE.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public GroupDTO group(@PathVariable(value = "groupId") Long id){
        return modelMapper.map(groupService.findGroupById(id), GroupDTO.class);
    }

    @PostMapping("/request{groupId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GroupReqDTO> createGroupReq(@PathVariable(value = "groupId") Long id, Principal userinfo) {

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

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public GroupDTO groups(){
        return modelMapper.map(groupService.availableGroups(), GroupDTO.class);
    }

    @GetMapping("/foryou")
    public GroupDTO groupsforyou(Principal userinfo){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return null;
        }
        return modelMapper.map(groupService.yourAvailableGroups(user), GroupDTO.class);
    }

    @GetMapping("/yourGroups")
    public GroupDTO yourGroups(Principal userinfo){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return null;
        }
        return modelMapper.map(groupService.groupsByYou(user), GroupDTO.class);
    }

    @GetMapping("/{groupId}/requestswaiting")
    public List<GroupReq> requestsforyou(Principal userinfo, @PathVariable(value = "groupId") Long id){
        User user = userService.findByUsername(userinfo.getName());
        if (user == null) {
            return null;
        }
        Group group = groupService.findGroupById(id);
        if (group.getGroupAdmin() == user){
            return groupService.waitingReqs(group);
        }
        return null;
    }

    @PostMapping("/request/accept{reqId}")
    public ResponseEntity<GroupReqDTO> acceptReq(Principal userInfo, @PathVariable(value = "reqId") Long id){
        User user = userService.findByUsername(userInfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        GroupReq proccessedGroupReq = groupService.findGroupReqById(id);
        proccessedGroupReq.setApproved(ERequestState.ACCEPTED);
        GroupReqDTO groupReqDTO = new GroupReqDTO(groupService.updateGroupReq(proccessedGroupReq));

        return new ResponseEntity(groupReqDTO, HttpStatus.CREATED);
    }

    @PostMapping("/request/reject{reqId}")
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
}
