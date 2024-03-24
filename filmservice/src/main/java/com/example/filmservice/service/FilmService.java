package com.example.filmservice.service;

import com.example.filmservice.dto.FilmDto;
import com.example.filmservice.entity.FilmEntity;
import com.example.filmservice.mapping.FilmGenreMapping;
import com.example.filmservice.mapping.FilmMapping;
import com.example.filmservice.repository.FilmGenreRepository;
import com.example.filmservice.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmMapping filmMapping;
    @Autowired
    private FilmGenreRepository filmGenreRepository;
    @Autowired
    private FilmGenreMapping filmGenreMapping;

    public void addFilm(FilmDto filmDto) {
        filmRepository.save(filmMapping.mapToFilmEntity(filmDto));
    }

    public FilmDto takeFilmById(long filmId) {
        return filmMapping.mapToFilmDto(filmRepository.findByFilmId(filmId));
    }

    public Page<FilmEntity> takeFilms(PageRequest pageRequest) {
        return filmRepository.findAll(pageRequest);
    }

    public List<FilmDto> takeFilmsByTitle(String name) {
        return filmRepository.findFilmEntitiesByFilmTitle(name)
                .stream().map(filmMapping::mapToFilmDto)
                .collect(Collectors.toList());
    }

    public void removeFilmById(long filmId) {
        filmRepository.deleteById(filmId);
    }

}
