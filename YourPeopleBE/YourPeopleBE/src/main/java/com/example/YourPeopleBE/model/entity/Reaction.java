package com.example.YourPeopleBE.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reactions")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private EReactionType reactionType;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    private User reactedBy;
    @ManyToOne
    private Post reactedOnPost;
    @ManyToOne
    private Comment reactedOnComment;
}
