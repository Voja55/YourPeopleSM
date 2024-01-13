package com.example.YourPeopleBE.model.dto;

import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.FriendReq;
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
public class FriendReqDTO {

    private Long id;

    private ERequestState approved;

    private LocalDateTime creationTime;

    private LocalDateTime answerTime;

    private User from;

    private User to;

    public FriendReqDTO(FriendReq newFriendReq) {
        this.id = newFriendReq.getId();
        this.approved = newFriendReq.getApproved();
        this.creationTime = newFriendReq.getCreationTime();
        this.answerTime = newFriendReq.getAnswerTime();
        this.from = newFriendReq.getFrom();
        this.to = newFriendReq.getTo();
    }
}
