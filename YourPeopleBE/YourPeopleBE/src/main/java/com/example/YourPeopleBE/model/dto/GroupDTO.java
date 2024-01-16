package com.example.YourPeopleBE.model.dto;

import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GroupDTO {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime creationDate;

    private User groupAdmin;

    private boolean suspended;

    private LocalDateTime suspendedTime;

    private String suspendedReason;

    public GroupDTO(Group createdGroup) {
        this.id = createdGroup.getId();
        this.name = createdGroup.getName();
        this.description = createdGroup.getDescription();
        this.creationDate = createdGroup.getCreationDate();
        this.groupAdmin = createdGroup.getGroupAdmin();
        this.suspended = createdGroup.isSuspended();
        this.suspendedTime = createdGroup.getSuspendedTime();
        this.suspendedReason = createdGroup.getSuspendedReason();
    }
}
