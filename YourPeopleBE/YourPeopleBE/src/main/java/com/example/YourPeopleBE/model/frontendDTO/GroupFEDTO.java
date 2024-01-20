package com.example.YourPeopleBE.model.frontendDTO;

import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GroupFEDTO {
    private Long id;

    private String name;

    private String description;

    private LocalDateTime creationDate;

    private String groupAdmin;

    public GroupFEDTO(GroupFEDTO createdGroup) {
        this.id = createdGroup.getId();
        this.name = createdGroup.getName();
        this.description = createdGroup.getDescription();
        this.creationDate = createdGroup.getCreationDate();
        this.groupAdmin = createdGroup.getGroupAdmin();
    }
}
