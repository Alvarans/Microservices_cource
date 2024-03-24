package com.example.rateservice.repository;

import com.example.rateservice.entity.FilmEntity;
import com.example.rateservice.entity.RatingEntity;
import com.example.rateservice.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
    List<RatingEntity> findRatingEntitiesByRatedFilm(FilmEntity film);

    List<RatingEntity> findRatingEntitiesByRatedReview(ReviewEntity review);
}
