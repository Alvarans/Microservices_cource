package com.example.reviewservice.repository;

import com.example.reviewservice.entity.FilmEntity;
import com.example.reviewservice.entity.ReviewEntity;
import com.example.reviewservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    ReviewEntity findByReviewId(Long id);

    List<ReviewEntity> findReviewEntitiesByUser(UserEntity user);

    List<ReviewEntity> findReviewEntitiesByFilm(FilmEntity film);

    List<ReviewEntity> findReviewEntitiesByFilm_FilmId(long filmId);

    List<ReviewEntity> findAllByOrderByReviewId();
}
