package com.example.YourPeopleBE.model.frontendDTO;

import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GroupReqFEDTO {

    private Long id;
    private ERequestState approved;
    private LocalDateTime creationTime;
    private LocalDateTime answerTime;
    private String from;
    private String groupName;
    private Long groupId;

    public GroupReqFEDTO(GroupReqFEDTO createdGroupReq) {
        this.id = createdGroupReq.getId();
        this.approved = createdGroupReq.getApproved();
        this.creationTime = createdGroupReq.getCreationTime();
        this.answerTime = createdGroupReq.getAnswerTime();
        this.from = createdGroupReq.getFrom();
        this.groupName = createdGroupReq.getGroupName();
        this.groupId = createdGroupReq.getGroupId();
    }
}
