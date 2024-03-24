package com.example.filmservice.dto;

import lombok.Data;

@Data
public class FilmDto {
    Long filmId;
    String filmTitle;
    short releaseDate;
    String director;
    short filmTiming;
    String annotation;
    short reviewerRating;
    short usersRating;
}
