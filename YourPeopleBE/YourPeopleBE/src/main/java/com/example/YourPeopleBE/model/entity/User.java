package com.example.YourPeopleBE.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column()
    private LocalDateTime lastLogin;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column()
    private String desc;

    @Column(nullable = false)
    private boolean sysadmin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groupAdmin")
    @JsonIgnore
    private Set<Group> adminOf = new HashSet<Group>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "from")
    @JsonIgnore
    private Set<FriendReq> sentFriendReqs = new HashSet<FriendReq>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "to")
    @JsonIgnore
    private Set<FriendReq> waitingFriendReqs = new HashSet<FriendReq>();
    //approved friend req is meaning it is a friend so no need for recursion


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "from")
    @JsonIgnore
    private Set<GroupReq> groupReqs = new HashSet<GroupReq>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "postedBy")
    @JsonIgnore
    private Set<Post> posts = new HashSet<Post>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "postedBy")
    @JsonIgnore
    private Set<Comment> comments = new HashSet<Comment>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reactedBy")
    @JsonIgnore
    private Set<Reaction> reactions = new HashSet<Reaction>();


}
