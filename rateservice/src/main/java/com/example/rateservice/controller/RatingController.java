package com.example.rateservice.controller;

import com.example.rateservice.dto.FilmDto;
import com.example.rateservice.dto.RatingDto;
import com.example.rateservice.dto.ReviewDto;
import com.example.rateservice.mapping.FilmMapping;
import com.example.rateservice.mapping.ReviewMapping;
import com.example.rateservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rateservice/rating")
@CrossOrigin(origins = "*")
public class RatingController {

    private final RatingService ratingService;
    private final FilmMapping filmMapping;
    private final ReviewMapping reviewMapping;

    @PostMapping("/addrating")
    public ResponseEntity<Integer> addRating(@RequestBody RatingDto ratingDto) {
        ratingService.addRate(ratingDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/filmrate")
    public int takeFilmRate(@RequestParam("id") Long filmId) {
        String uri = "http://localhost:8082/api/filmservice/film/takefilm?id=" + filmId;
        RestTemplate restTemplate = new RestTemplate();
        FilmDto filmDto = restTemplate.getForObject(uri, FilmDto.class);
        return filmDto != null
                ? ratingService.calculateFilmUserRate(filmMapping.mapToFilmEntity(filmDto))
                : 0;
    }

    @GetMapping("/reviewrate")
    public int takeReviewRate(@RequestParam("id") Long reviewId) {
        String uri = "http://localhost:8083/api/reviewservice/review/takereview?id=" + reviewId;
        RestTemplate restTemplate = new RestTemplate();
        ReviewDto reviewDto = restTemplate.getForObject(uri, ReviewDto.class);
        return reviewDto != null
                ? ratingService.calculateReviewRate(reviewMapping.mapToReviewEntity(reviewDto))
                : 0;
    }
}
