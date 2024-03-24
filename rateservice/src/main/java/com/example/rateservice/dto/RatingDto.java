package com.example.rateservice.dto;

import lombok.Data;

@Data
public class RatingDto {
    long ratingId;
    short ratingValue;
    Long ratedFilm;
    Long ratedUser;
    Long ratedReview;
}
