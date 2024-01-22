package com.example.YourPeopleBE.controllers;

import com.example.YourPeopleBE.model.dto.*;
import com.example.YourPeopleBE.model.entity.ERequestState;
import com.example.YourPeopleBE.model.entity.FriendReq;
import com.example.YourPeopleBE.model.entity.GroupReq;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.model.frontendDTO.FriendFEDTO;
import com.example.YourPeopleBE.security.TokenUtils;
import com.example.YourPeopleBE.service.IFriendsService;
import com.example.YourPeopleBE.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    final IUserService userService;

    final IFriendsService friendsService;

    final PasswordEncoder passwordEncoder;

    final ModelMapper modelMapper;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    public UserController(IUserService userService, IFriendsService friendsService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userService = userService;
        this.friendsService = friendsService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> registarion(@RequestBody @Validated UserDTO newUser){
        User createdUser = userService.createUser(newUser);
        if (createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO userDTO = new UserDTO(createdUser);
        return new ResponseEntity(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> usersList() {
        return userService.getAllUsers();
    }

    @GetMapping("/search/{searchParam}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<User> searchUsers(@PathVariable(value = "searchParam") String searchParam) {
        return userService.searchUsers(searchParam);
    }

    @GetMapping("/profile")
    @CrossOrigin
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public UserDTO user(Principal user) {
        return modelMapper.map(userService.findByUsername(user.getName()), UserDTO.class);
    }

    @GetMapping("/getuser/{username}")
    @CrossOrigin
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public UserDTO user(@PathVariable(value = "username")String username) {
        return modelMapper.map(userService.findByUsername(username), UserDTO.class);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/change-password", consumes = "application/json")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> ChangePassword(@RequestBody @Validated PassChangeDTO newPass, Principal user) {

        User newpassUser = userService.findByUsername(user.getName());
        if (newpassUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!newPass.getNewPass1().equals(newPass.getNewPass2())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        newpassUser.setPassword(passwordEncoder.encode(newPass.getNewPass1()));
        newpassUser = userService.updateUserInfo(newpassUser);

        return new ResponseEntity(null, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/edit", consumes = "application/json")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Validated UserDTO userDTO, Principal user) {

        User editedUser = userService.findByUsername(user.getName());

        if (editedUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //TODO:Provera ako je null da ne menja nista
        editedUser.setEmail(userDTO.getEmail());
        editedUser.setName(userDTO.getName());
        editedUser.setSurname(userDTO.getSurname());
        editedUser.setDescription(userDTO.getDescription());

        editedUser = userService.updateUserInfo(editedUser);

        return new ResponseEntity<>(new UserDTO(editedUser), HttpStatus.OK);
    }

    //--------------------------FRIENDS---------------------------------------

    @PostMapping("/friend/send/{username}")
    public ResponseEntity<FriendReq> acceptReq(Principal userInfo, @PathVariable(value = "username") String username){
        User you = userService.findByUsername(userInfo.getName());
        if (you == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        if(friendsService.alreadyFriends(you, user)){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        FriendReq friendReq = friendsService.sendFriendReq(you, user);
        if (friendReq == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(friendReq, HttpStatus.CREATED);
    }

    @GetMapping(value = "friends/waiting")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<FriendReq> waitingReqs(Principal userInfo){
        User user = userService.findByUsername(userInfo.getName());
        if (user == null) {
            return null;
        }
        List<FriendReq> friendReqs = friendsService.waitingRequestForYou(user);
        return friendReqs;
    }

    @GetMapping(value = "friends")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<FriendFEDTO> friends(Principal userInfo){
        User user = userService.findByUsername(userInfo.getName());
        if (user == null) {
            return null;
        }
        List<User> friends= friendsService.yourFriends(user);
        List<FriendFEDTO> friendsResponse = new ArrayList<>();
        for (User friend: friends){
            FriendFEDTO friendCopy = new FriendFEDTO();
            FriendReq friendReq = friendsService.findFriendReqByFromAndTo(friend, user);
            friendCopy.setFriendReqId(friendReq.getId());
            friendCopy.setId(friend.getId());
            friendCopy.setUsername(friend.getUsername());
            friendCopy.setEmail(friend.getEmail());
            friendCopy.setName(friend.getName());
            friendCopy.setSurname(friend.getSurname());
            friendCopy.setDescription(friend.getDescription());

            friendsResponse.add(friendCopy);
        }
        return friendsResponse;
    }

    @PostMapping("/friend/accept{reqId}")
    public ResponseEntity<FriendReqDTO> acceptReq(Principal userInfo, @PathVariable(value = "reqId") Long id){
        User user = userService.findByUsername(userInfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        FriendReq proccessedFR = friendsService.findFriendReqById(id);
        proccessedFR.setApproved(ERequestState.ACCEPTED);
        FriendReqDTO friendReqDTO =new FriendReqDTO(friendsService.updateFriendReq(proccessedFR));

        return new ResponseEntity(friendReqDTO, HttpStatus.CREATED);
    }

    @PostMapping("/friend/decline{reqId}")
    public ResponseEntity<FriendReqDTO> declineReq(Principal userInfo, @PathVariable(value = "reqId") Long id){
        User user = userService.findByUsername(userInfo.getName());
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        FriendReq proccessedFR = friendsService.findFriendReqById(id);
        proccessedFR.setApproved(ERequestState.REJECTED);
        FriendReqDTO friendReqDTO =new FriendReqDTO(friendsService.updateFriendReq(proccessedFR));

        return new ResponseEntity(friendReqDTO, HttpStatus.CREATED);
    }



}
