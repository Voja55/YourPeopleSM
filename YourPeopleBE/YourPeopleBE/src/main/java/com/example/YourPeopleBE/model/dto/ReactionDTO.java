package com.example.YourPeopleBE.model.dto;

import com.example.YourPeopleBE.model.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReactionDTO {

    private Long id;

    private EReactionType reactionType;

    private LocalDateTime timestamp;

    private User reactedBy;

    private Post reactedOnPost;

    private Comment reactedOnComment;

    public ReactionDTO(Reaction createdReaction) {
        this.id = createdReaction.getId();
        this.reactionType = createdReaction.getReactionType();
        this.timestamp = createdReaction.getTimestamp();
        this.reactedBy = createdReaction.getReactedBy();
        this.reactedOnPost = createdReaction.getReactedOnPost();
        this.reactedOnComment = createdReaction.getReactedOnComment();
    }
}
