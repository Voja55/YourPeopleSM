package com.example.YourPeopleBE.model.dto;

import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.Reaction;
import com.example.YourPeopleBE.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    private String text;

    private LocalDateTime creationDate;

    private User postedBy;

    private Post commentedOn;

    private Comment repliesTo;

    public CommentDTO(Comment createdComment) {
        this.id = createdComment.getId();
        this.text = createdComment.getText();
        this.creationDate = createdComment.getCreationDate();
        this.postedBy = createdComment.getPostedBy();
        this.commentedOn = createdComment.getCommentedOn();
        this.repliesTo = createdComment.getRepliesTo();
    }
}
