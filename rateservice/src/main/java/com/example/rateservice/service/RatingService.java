package com.example.rateservice.service;

import com.example.rateservice.dto.RatingDto;
import com.example.rateservice.entity.FilmEntity;
import com.example.rateservice.entity.RatingEntity;
import com.example.rateservice.entity.ReviewEntity;
import com.example.rateservice.mapping.RatingMapping;
import com.example.rateservice.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingMapping ratingMapping;
    private final RatingRepository ratingRepository;

    public void addRate(RatingDto ratingDto) {
        RatingDto newRateDto = new RatingDto();
        newRateDto.setRatingValue(ratingDto.getRatingValue());
        newRateDto.setRatedUser(ratingDto.getRatedUser());
        newRateDto.setRatedFilm(ratingDto.getRatedFilm());
        newRateDto.setRatedReview(ratingDto.getRatedReview());
        ratingRepository.save(ratingMapping.mapToRatingEntity(newRateDto));
    }

    public int calculateFilmUserRate(FilmEntity film) {
        List<RatingEntity> ratings = ratingRepository.findRatingEntitiesByRatedFilm(film);
        if (ratings.isEmpty())
            return 0;
        int rateSum = 0;
        for (RatingEntity rate : ratings) {
            rateSum += rate.getRatingValue();
        }
        return rateSum / ratings.size();
    }

    public int calculateReviewRate(ReviewEntity review) {
        try {
            List<RatingEntity> ratings = ratingRepository.findRatingEntitiesByRatedReview(review);
            if (ratings.isEmpty())
                return 0;
            int rateSum = 0;
            for (RatingEntity rate : ratings) {
                rateSum += rate.getRatingValue();
            }
            return rateSum / ratings.size();
        } catch (Throwable throwable) {
            return 0;
        }
    }
}
