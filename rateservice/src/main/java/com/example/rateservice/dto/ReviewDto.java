package com.example.rateservice.dto;

import lombok.Data;

@Data
public class ReviewDto {
    Long reviewId;
    String reviewTitle;
    String authorOrganization;
    String reviewText;
    short filmRate;
    short reviewRate;
    Long film;
    Long user;

}
