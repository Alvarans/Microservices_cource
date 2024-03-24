package com.example.filmservice.controller;

import com.example.filmservice.dto.FilmDto;
import com.example.filmservice.mapping.FilmMapping;
import com.example.filmservice.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filmservice/film")
@CrossOrigin(origins = "*")
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmMapping filmMapping;

    @PostMapping("/addfilm")
    public ResponseEntity<Long> addFilm(@RequestBody FilmDto filmDto) {
        filmService.addFilm(filmDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/takefilm")
    public FilmDto takeFilmById(@RequestParam("id") Long filmId) {
        return filmService.takeFilmById(filmId);
    }

    @GetMapping("/takefilms")
    public List<FilmDto> takeAllFilmsByPages(@RequestParam(required = false, defaultValue = "0") int page,
                                             @RequestParam(required = false, defaultValue = "10") int size) {
        return filmService.takeFilms(PageRequest.of(page, size)).getContent()
                .stream().map(filmMapping::mapToFilmDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/takefilmsbytitle")
    public List<FilmDto> takeFilmsByTitle(@RequestParam("title") String title) {
        return filmService.takeFilmsByTitle(title);
    }

    @DeleteMapping("/removefilm")
    public ResponseEntity<Long> removeFilm(@RequestParam("id") Long filmId) {
        filmService.removeFilmById(filmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
