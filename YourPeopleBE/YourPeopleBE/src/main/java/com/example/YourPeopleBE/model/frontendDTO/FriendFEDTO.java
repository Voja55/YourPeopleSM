package com.example.YourPeopleBE.model.frontendDTO;

import com.example.YourPeopleBE.model.entity.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FriendFEDTO {

    private Long id;

    private String username;

    private String email;

    private String name;

    private String surname;

    private String description;

    private Long friendReqId;

    public FriendFEDTO(FriendFEDTO createFriend) {
        this.id = createFriend.getId();
        this.username = createFriend.getUsername();
        this.email = createFriend.getEmail();
        this.name = createFriend.getName();
        this.surname = createFriend.getSurname();
        this.description = createFriend.getDescription();
        this.friendReqId = createFriend.getFriendReqId();
    }
}
