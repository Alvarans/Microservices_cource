package com.example.rateservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;
    @Column(name = "review_title", unique = true, nullable = false)
    private String reviewTitle;
    @Column(name = "author_organization")
    private String authorOrganization;
    @Column(name = "review_text", nullable = false)
    private String reviewText;
    @Column(name = "film_rate", nullable = false)
    private short filmRate;
    @Column(name = "review_rate")
    private short reviewRate;
    //Film
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private FilmEntity film;
    //User
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;
    //Additions
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "review")
    @Column(name = "review_additions")
    private Set<AdditionEntity> reviewAddition = new HashSet<>();
    //Reports
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "reportedReview")
    @Column(name = "review_reports")
    private Set<ReportEntity> reviewReports = new HashSet<>();
    //Comments
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "commentedReview")
    @Column(name = "review_comments")
    private Set<CommentEntity> reviewComments = new HashSet<>();
    //Rating
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ratedReview")
    @Column(name = "review_rates")
    private Set<RatingEntity> reviewRates = new HashSet<>();
}
