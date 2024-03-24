package com.example.reviewservice.repository;

import com.example.reviewservice.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    FilmEntity findByFilmId(Long Id);

}
