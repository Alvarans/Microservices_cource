package com.example.rateservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;
    @Column(name = "film_title", nullable = false)
    private String filmTitle;
    @Column(name = "release_date", nullable = false)
    private short releaseDate;
    @Column(name = "director", nullable = false)
    private String director;
    @Column(name = "film_timing", nullable = false)
    private short filmTiming;
    @Column(name = "annotation", nullable = false)
    private String annotation;
    @Column(name = "reviewer_rating")
    private short reviewerRating;
    @Column(name = "users_rating")
    private short usersRating;
    //Reviews
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "film")
    private Set<ReviewEntity> reviews = new HashSet<>();
    //Comments
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commentedFilm")
    private Set<CommentEntity> comments = new HashSet<>();
    //Rating
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ratedFilm")
    private Set<RatingEntity> rates = new HashSet<>();
    //Film genres
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "filmgenre",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<FilmGenreEntity> genres = new HashSet<>();

    public void AddGenre(FilmGenreEntity filmGenre) {
        this.genres.add(filmGenre);
        filmGenre.getFilms().add(this);
    }

    public void removeGenre(FilmGenreEntity filmGenre) {
        this.genres.remove(filmGenre);
        filmGenre.getFilms().remove(this);
    }
}
