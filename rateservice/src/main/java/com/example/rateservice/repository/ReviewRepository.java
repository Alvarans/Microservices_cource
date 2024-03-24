package com.example.rateservice.repository;

import com.example.rateservice.entity.ReviewEntity;
import com.example.rateservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    ReviewEntity findByReviewId(Long id);
    List<ReviewEntity> findReviewEntitiesByUser(UserEntity user);
}
