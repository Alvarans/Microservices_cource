package com.example.rateservice.mapping;

import com.example.rateservice.dto.ReviewDto;
import com.example.rateservice.entity.ReviewEntity;
import com.example.rateservice.repository.FilmRepository;
import com.example.rateservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMapping {
    //@Autowired
    private final FilmRepository filmRepository;
    //@Autowired
    private final UserRepository userRepository;

    public ReviewEntity mapToReviewEntity(ReviewDto dto){
        ReviewEntity tempEntity = new ReviewEntity();
        tempEntity.setReviewId(dto.getReviewId());
        tempEntity.setReviewTitle(dto.getReviewTitle());
        tempEntity.setAuthorOrganization(dto.getAuthorOrganization());
        tempEntity.setReviewText(dto.getReviewText());
        tempEntity.setFilmRate(dto.getFilmRate());
        tempEntity.setReviewRate(dto.getReviewRate());
        tempEntity.setFilm(filmRepository.findByFilmId(dto.getFilm()));
        tempEntity.setUser(userRepository.findByUserId(dto.getUser()));
        return tempEntity;
    }

    public ReviewDto mapToReviewDto(ReviewEntity entity){
        ReviewDto tempDto = new ReviewDto();
        tempDto.setReviewId(entity.getReviewId());
        tempDto.setReviewTitle(entity.getReviewTitle());
        tempDto.setAuthorOrganization(entity.getAuthorOrganization());
        tempDto.setReviewText(entity.getReviewText());
        tempDto.setFilmRate(entity.getFilmRate());
        tempDto.setReviewRate(entity.getReviewRate());
        tempDto.setFilm(entity.getFilm().getFilmId());
        tempDto.setUser(entity.getUser().getUserId());
        return tempDto;
    }
}
