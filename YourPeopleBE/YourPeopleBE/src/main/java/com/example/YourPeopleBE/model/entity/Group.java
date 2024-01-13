package com.example.YourPeopleBE.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @ManyToOne
    private User groupAdmin;

    @Column(nullable = false)
    private boolean suspended;
    @Column
    private LocalDateTime suspendedTime;
    @Column
    private String suspendedReason;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    @JsonIgnore
    private Set<GroupReq> requests = new HashSet<GroupReq>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "postedIn")
    @JsonIgnore
    private Set<Post> posts = new HashSet<Post>();



}
