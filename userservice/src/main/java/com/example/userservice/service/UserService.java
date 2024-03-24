package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.mapping.UserMapping;
import com.example.userservice.repository.RolesRepository;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;
    private final RolesRepository rolesRepository;

    public void addUser(UserDto userDto) {
        userRepository.save(userMapping.mapToUserEntity(userDto));
    }

    public UserEntity getUserById(long userId) {
        return userRepository.findByUserId(userId);
    }

    public UserEntity getUserByNickname(String nick) {
        return userRepository.findByUserNickname(nick);
    }

    public List<UserDto> getUsers(){
        return userRepository.findAllByOrderByUserId().stream().map(userMapping::mapToUserDto).collect(Collectors.toList());
    }
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public String takeUserRole(long userId){
        UserEntity user = userRepository.findByUserId(userId);
        return user.getUserRole().getRole();
    }

    @Scheduled(cron = "@yearly")
    public void upRate() {
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity user : users) {
            user.setUserRating((short) (user.getUserRating() + 10));
        }
    }

}
