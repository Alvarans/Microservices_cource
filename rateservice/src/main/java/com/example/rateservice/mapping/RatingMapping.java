package com.example.rateservice.mapping;

import com.example.rateservice.dto.RatingDto;
import com.example.rateservice.entity.RatingEntity;
import com.example.rateservice.repository.FilmRepository;
import com.example.rateservice.repository.ReviewRepository;
import com.example.rateservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingMapping {
    //@Autowired
    private final FilmRepository filmRepository;
    //@Autowired
    private final UserRepository userRepository;
    //@Autowired
    private final ReviewRepository reviewRepository;
    public RatingDto mapToRatingDto(RatingEntity entity){
        RatingDto tempDto = new RatingDto();
        tempDto.setRatingId(entity.getRatingId());
        //tempDto.setRatingType(entity.getRatingType());
        tempDto.setRatingValue(entity.getRatingValue());
        tempDto.setRatedFilm(entity.getRatedFilm().getFilmId());
        tempDto.setRatedUser(entity.getRatedUser().getUserId());
        tempDto.setRatedReview(entity.getRatedReview().getReviewId());
        return tempDto;
    }

    public RatingEntity mapToRatingEntity(RatingDto dto){
        RatingEntity tempEntity = new RatingEntity();
        //tempEntity.setRatingId(dto.getRatingId());
        //tempEntity.setRatingType(dto.getRatingType());
        tempEntity.setRatingValue(dto.getRatingValue());
        tempEntity.setRatedFilm(filmRepository.findByFilmId(dto.getRatedFilm()));
        tempEntity.setRatedReview(reviewRepository.findByReviewId(dto.getRatedReview()));
        if(tempEntity.getRatedReview() != null)
            tempEntity.setRatedUser(userRepository.findByUserId(tempEntity.getRatedReview().getUser().getUserId()));
        else
            tempEntity.setRatedUser(null);
        return tempEntity;
    }
}
