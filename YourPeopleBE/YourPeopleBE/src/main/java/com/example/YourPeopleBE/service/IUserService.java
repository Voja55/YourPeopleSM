package com.example.YourPeopleBE.service;

import com.example.YourPeopleBE.model.dto.UserDTO;
import com.example.YourPeopleBE.model.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(UserDTO userDTO);
    List<User> getAllUsers();
    List<User> searchUsers(String searchparam);
    User findByUsername(String username);
    User findByid(Long id);
    User updateUserInfo(User user);
}
