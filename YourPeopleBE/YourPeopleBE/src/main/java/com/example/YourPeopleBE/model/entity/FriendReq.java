package com.example.YourPeopleBE.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "friendReqs")
public class FriendReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ERequestState approved;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationTime;

    @Column
    private LocalDateTime answerTime;

    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
}
