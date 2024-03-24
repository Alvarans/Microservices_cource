package com.example.reviewservice.dto;

import lombok.Data;

@Data
public class UserDto {
    long userId;
    String userNickname;
    String userEmail;
    String userRole;
    short userRating;
    short reviewerRating;
}
