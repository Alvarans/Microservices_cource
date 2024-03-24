package com.example.reviewservice.service;

import com.example.reviewservice.dto.ReviewDto;
import com.example.reviewservice.entity.FilmEntity;
import com.example.reviewservice.entity.ReviewEntity;
import com.example.reviewservice.entity.UserEntity;
import com.example.reviewservice.mapping.ReviewMapping;
import com.example.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapping reviewMapping;

    public void addReview(ReviewDto reviewDto) {
        reviewRepository.save(reviewMapping.mapToReviewEntity(reviewDto));
    }

    public void removeReviewById(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public ReviewDto takeReviewById(long reviewId) {
        return reviewMapping.mapToReviewDto(reviewRepository.findByReviewId(reviewId));
    }

    public List<ReviewEntity> takeAllReviews(){
        return reviewRepository.findAllByOrderByReviewId();
    }
    public Page<ReviewEntity> takeReviewsByPage(PageRequest pageRequest) {
        return reviewRepository.findAll(pageRequest);
    }

    public List<ReviewEntity> takeAllReviewsForUser(UserEntity userEntity) {
        return reviewRepository.findReviewEntitiesByUser(userEntity);
    }

    public List<ReviewEntity> takeAllReviewsForFilm(FilmEntity filmEntity){
        return reviewRepository.findReviewEntitiesByFilm(filmEntity);
    }

    public int calculateFilmReviewRating(long filmId){
        List<ReviewEntity> reviews = reviewRepository.findReviewEntitiesByFilm_FilmId(filmId);
        int result = 0;
        for(ReviewEntity review : reviews){
            result+=review.getReviewRate();
        }
        return result;
    }
}
