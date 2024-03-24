package com.example.filmservice.repository;

import com.example.filmservice.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    FilmEntity findByFilmId(Long Id);

    List<FilmEntity> findFilmEntitiesByFilmTitle(String filmTitle);

    List<FilmEntity> findFilmEntitiesByGenres_GenreId(int genreid);
}
