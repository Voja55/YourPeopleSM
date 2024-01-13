package com.example.YourPeopleBE.model.dto;

import com.example.YourPeopleBE.model.entity.Group;
import com.example.YourPeopleBE.model.entity.Post;
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
public class PostDTO {

    private Long id;

    private String content;

    private LocalDateTime creationDate;

    private User postedBy;

    private Group postedIn;

    public PostDTO(Post createdPost) {
        this.id = createdPost.getId();
        this.content = createdPost.getContent();
        this.creationDate = createdPost.getCreationDate();
        this.postedBy = createdPost.getPostedBy();
        this.postedIn = createdPost.getPostedIn();
    }
}
