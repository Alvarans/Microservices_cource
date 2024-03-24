package com.example.userservice.mapping;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserMapping {

    private final RolesRepository rolesRepository;
    public UserEntity mapToUserEntity(UserDto dto){
        UserEntity tempEntity = new UserEntity();
        tempEntity.setUserId(dto.getUserId());
        tempEntity.setUserNickname(dto.getUserNickname());
        tempEntity.setUserRating(dto.getUserRating());
        tempEntity.setUserRole(rolesRepository.findByRole(dto.getUserRole()));
        tempEntity.setUserEmail(dto.getUserEmail());
        tempEntity.setReviewerRating(dto.getReviewerRating());
        return tempEntity;
    }

    public UserDto mapToUserDto(UserEntity entity){
        UserDto tempDto = new UserDto();
        tempDto.setUserId(entity.getUserId());
        tempDto.setUserNickname(entity.getUserNickname());
        tempDto.setUserEmail(entity.getUserEmail());
        tempDto.setUserRole(entity.getUserRole().getRole());
        tempDto.setUserRating(entity.getUserRating());
        tempDto.setReviewerRating(entity.getReviewerRating());
        return tempDto;
    }
}
