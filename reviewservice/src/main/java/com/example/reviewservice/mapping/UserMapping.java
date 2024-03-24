package com.example.reviewservice.mapping;

import com.example.reviewservice.dto.UserDto;
import com.example.reviewservice.entity.UserEntity;
import com.example.reviewservice.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapping {

    private final RolesRepository rolesRepository;

    public UserEntity mapToUserEntity(UserDto dto) {
        UserEntity tempEntity = new UserEntity();
        tempEntity.setUserId(dto.getUserId());
        tempEntity.setUserNickname(dto.getUserNickname());
        tempEntity.setUserRating(dto.getUserRating());
        tempEntity.setUserRole(rolesRepository.findByRole(dto.getUserRole()));
        tempEntity.setUserEmail(dto.getUserEmail());
        tempEntity.setReviewerRating(dto.getReviewerRating());
        return tempEntity;
    }

    public UserDto mapToUserDto(UserEntity entity) {
        UserDto tempDto = new UserDto();
        tempDto.setUserId(entity.getUserId());
        tempDto.setUserNickname(entity.getUserNickname());
        tempDto.setUserRating(entity.getUserRating());
        tempDto.setUserRole(entity.getUserRole().getRole());
        tempDto.setUserEmail(entity.getUserEmail());
        tempDto.setReviewerRating(entity.getReviewerRating());
        return tempDto;
    }
}
