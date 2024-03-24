package com.example.filmservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "comment_text", nullable = false)
    private String commentText;
    @Column(name = "comment_time", nullable = false)
    private Timestamp commentTime;
    //Reports
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reportedComment")
    Set<ReportEntity> reports = new HashSet<>();
    //Film
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id")
    private FilmEntity commentedFilm;
    //User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;
    //Review
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", referencedColumnName = "review_id")
    private ReviewEntity commentedReview;
}
