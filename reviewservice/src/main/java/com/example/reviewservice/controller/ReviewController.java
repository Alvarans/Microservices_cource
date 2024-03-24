package com.example.reviewservice.controller;

import com.example.reviewservice.dto.ReviewDto;
import com.example.reviewservice.dto.UserDto;
import com.example.reviewservice.entity.ReviewEntity;
import com.example.reviewservice.mapping.ReviewMapping;
import com.example.reviewservice.mapping.UserMapping;
import com.example.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviewservice/review")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapping reviewMapping;
    private final UserMapping userMapping;


    @PostMapping("/addreview")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDto reviewDto) {
        reviewService.addReview(reviewDto);
        return new ResponseEntity<>(reviewDto.getReviewId(), HttpStatus.CREATED);
    }

    @GetMapping("/takereview")
    public ReviewDto takeReview(@RequestParam("id") Long reviewId) {
        return reviewService.takeReviewById(reviewId);
    }

    @GetMapping("/getreviews")
    public List<ReviewDto> takeAllReviews() {
        return reviewService.takeAllReviews()
                .stream().map(reviewMapping::mapToReviewDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/takefilmreviewrate")
    public int takeFilmReviewRate(@RequestParam("id") Long filmId) {
        return reviewService.calculateFilmReviewRating(filmId);
    }

    @GetMapping("/takereviewerrate")
    public short takeReviewerRate(@RequestParam("id") Long userId) {
        String url = "http://localhost:8084/api/userservice/user/takeuserbyid?id=" + userId;
        RestTemplate restTemplate = new RestTemplate();
        UserDto userDto = restTemplate.getForObject(url, UserDto.class);
        if (userDto == null)
            return 0;
        List<ReviewEntity> reviews = reviewService.takeAllReviewsForUser(userMapping.mapToUserEntity(userDto));
        int rating = 0;
        for (ReviewEntity review : reviews) {
            url = "http://localhost:8081/api/rateservice/rating/reviewrate?id=" + review.getReviewId();
            rating += restTemplate.getForObject(url, Integer.class);
        }
        return (short) (rating / reviews.size());
    }

    @DeleteMapping("/removereview")
    public ResponseEntity<Long> removeReview(@RequestParam("id") Long reviewId) {
        reviewService.removeReviewById(reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
