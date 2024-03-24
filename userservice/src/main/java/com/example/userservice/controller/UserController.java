package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapping.UserMapping;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userservice/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final UserMapping userMapping;

    @PostMapping("/adduser")
    public ResponseEntity<Long> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/takeuserbyid")
    public UserDto takeUserById(@RequestParam("id") Long userId) {
        return userMapping.mapToUserDto(userService.getUserById(userId));
    }

    @GetMapping("/takeuserbynickname")
    public UserDto takeUserByNickname(@RequestParam("nickname") String nickname) {
        return userMapping.mapToUserDto(userService.getUserByNickname(nickname));
    }

    @GetMapping("/takeuserrole")
    public String takeUserRole(@RequestParam("id") Long userId) {
        return userService.takeUserRole(userId);
    }

    @DeleteMapping("/removeuser")
    public ResponseEntity<Long> removeUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getusers")
    public List<UserDto> takeAllUsers() {
        return userService.getUsers();
    }
}
