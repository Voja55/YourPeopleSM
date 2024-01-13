package com.example.YourPeopleBE.model.dto;

import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GroupReqDTO {

    private Long id;

    private ERequestState approved;

    private LocalDateTime creationTime;

    private LocalDateTime answerTime;

    private User from;

    private Group group;

    public GroupReqDTO(GroupReq createdGroupReq) {
        this.id = createdGroupReq.getId();
        this.approved = createdGroupReq.getApproved();
        this.creationTime = createdGroupReq.getCreationTime();
        this.answerTime = createdGroupReq.getAnswerTime();
        this.from = createdGroupReq.getFrom();
        this.group = createdGroupReq.getGroup();
    }
}
