package com.example.YourPeopleBE.model.frontendDTO;

import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostFEDTO {
    private Long id;
    private String content;
    private LocalDateTime creationDate;
    private String username;
    private String groupName;
    private Long groupId;
    private Integer reactionStat;

    public PostFEDTO(PostFEDTO createdPost) {
        this.id = createdPost.getId();
        this.content = createdPost.getContent();
        this.creationDate = createdPost.getCreationDate();
        this.username = createdPost.getUsername();
        this.groupName = createdPost.getGroupName();
        this.groupId = createdPost.getGroupId();
        this.reactionStat = createdPost.getReactionStat();
    }
}
