package com.example.YourPeopleBE.model.dto;

import com.example.YourPeopleBE.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private LocalDateTime lastLogin;

    private String name;

    private String surname;

    private String desc;

    private boolean sysadmin;

    public UserDTO(User createdUser) {
        this.id = createdUser.getId();
        this.username = createdUser.getUsername();
        this.password = createdUser.getPassword();
        this.email = createdUser.getEmail();
        this.lastLogin = createdUser.getLastLogin();
        this.name = createdUser.getName();
        this.surname = createdUser.getSurname();
        this.desc = createdUser.getDesc();
        this.sysadmin = createdUser.isSysadmin();
    }
}