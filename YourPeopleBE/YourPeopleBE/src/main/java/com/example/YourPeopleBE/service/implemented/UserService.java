package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.UserDTO;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Override
    public User createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<User> searchUsers(String searchparam) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User updateUserInfo(User user) {
        return null;
    }
}
