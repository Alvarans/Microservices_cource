package com.example.filmservice.repository;

import com.example.filmservice.entity.FilmGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmGenreRepository extends JpaRepository<FilmGenreEntity, Short> {
    List<FilmGenreEntity> findFilmGenreEntitiesByFilms_filmId(Long filmId);
}
