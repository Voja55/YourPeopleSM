package com.example.YourPeopleBE.model.frontendDTO;

import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentFEDTO {

    private Long id;

    private String text;

    private LocalDateTime creationDate;

    private String postedBy;

    private Long commentedOn;

    private Long repliesTo;

    private Integer reactions;

    public CommentFEDTO(Comment createdComment) {
        this.id = createdComment.getId();
        this.text = createdComment.getText();
        this.creationDate = createdComment.getCreationDate();
        this.postedBy = createdComment.getPostedBy().getUsername();
        this.commentedOn = createdComment.getCommentedOn().getId();
    }

    public CommentFEDTO(Comment createdComment, String randomstring) {
        this.id = createdComment.getId();
        this.text = createdComment.getText();
        this.creationDate = createdComment.getCreationDate();
        this.postedBy = createdComment.getPostedBy().getUsername();
        this.repliesTo = createdComment.getRepliesTo().getId();
    }
    public CommentFEDTO(CommentFEDTO createdComment) {
        this.id = createdComment.getId();
        this.text = createdComment.getText();
        this.creationDate = createdComment.getCreationDate();
        this.postedBy = createdComment.getPostedBy();
        this.commentedOn = createdComment.getCommentedOn();
        this.repliesTo = createdComment.getRepliesTo();
        this.reactions = createdComment.getReactions();
    }
}
