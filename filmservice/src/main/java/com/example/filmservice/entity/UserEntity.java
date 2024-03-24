package com.example.filmservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_nickname", unique = true, nullable = false)
    private String userNickname;
    @Column(name = "user_email", unique = true, nullable = false)
    private String userEmail;
    @Column(name = "user_fio")
    private String userFio;
    @Column(name = "user_gender")
    private String gender;
    @Column(name = "user_socials")
    private String userSocials;
    @Column(name = "user_rating")
    private short userRating = 50;
    @Column(name = "user_reviewer_rating")
    private short reviewerRating = 0;
    //Reviews
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @Column(name = "user_reviews")
    private Set<ReviewEntity> userReviews = new HashSet<>();
    //Comments
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @Column(name = "user_comments")
    private Set<CommentEntity> userComments = new HashSet<>();
    //Rates
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ratedUser")
    @Column(name = "user_rates")
    private Set<RatingEntity> userRates = new HashSet<>();
    //Role
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roles_id", referencedColumnName = "roles_id")
    private RolesEntity userRole;
    //Reports
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reportedUser")
    @Column(name = "user_reports")
    private Set<ReportEntity> userReports = new HashSet<>();
}
