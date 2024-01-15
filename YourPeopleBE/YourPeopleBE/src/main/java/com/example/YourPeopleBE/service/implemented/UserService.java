package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.UserDTO;
import com.example.YourPeopleBE.model.entity.ERole;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.repositories.UserRepo;
import com.example.YourPeopleBE.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    final UserRepo userRepo;

    final PasswordEncoder passEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passEncoder) {
        this.userRepo = userRepo;
        this.passEncoder = passEncoder;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        Optional<User> user = userRepo.findFirstByUsername(userDTO.getUsername());
        if (user.isPresent()){
            return null;
        }
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passEncoder.encode(userDTO.getPassword()));
        newUser.setEmail(userDTO.getEmail());
        newUser.setName(userDTO.getName());
        newUser.setSurname(userDTO.getSurname());
        newUser.setDesc(userDTO.getDesc());
        newUser.setRole(ERole.USER);
        newUser = userRepo.save(newUser);
        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    public List<User> searchUsers(String searchparam) {
        List<User> searchedUsers = userRepo.findAllByNameContainsOrSurnameContains(searchparam);
        return searchedUsers;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepo.findFirstByUsername(username);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public User findByid(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public User updateUserInfo(User user) {
        return userRepo.save(user);
    }
}
