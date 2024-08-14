package com.example.InstaPrj.service;
import com.example.InstaPrj.dto.ReviewsDTO;
import com.example.InstaPrj.entity.ClubMember;
import com.example.InstaPrj.entity.Feeds;
import com.example.InstaPrj.entity.Reviews;

import java.util.List;

public interface ReviewsService {
  List<ReviewsDTO> getListOfMovie(Long mno);
  
  Long register(ReviewsDTO reviewDTO);

  void modify(ReviewsDTO reviewDTO);

  void remove(Long reviewnum);

  public default Reviews dtoToEntity(ReviewsDTO reviewDTO) {
    Reviews review = Reviews.builder()
        .reviewnum(reviewDTO.getReviewnum())
        .feeds(Feeds.builder().fno(reviewDTO.getMno()).build())
        .clubMember(ClubMember.builder().cno(reviewDTO.getMid()).build())
        .grade(reviewDTO.getGrade())
        .text(reviewDTO.getText())
        .build();
    return review;
  }

  default ReviewsDTO entityToDto(Reviews reviews) {
    ReviewsDTO reviewDTO = ReviewsDTO.builder()
        .reviewnum(reviews.getReviewnum())
        .mno(reviews.getFeeds().getFno())
        .mid(reviews.getClubMember().getCno())
        .nickname(reviews.getClubMember().getName())
        .email(reviews.getClubMember().getEmail())
        .grade(reviews.getGrade())
        .text(reviews.getText())
        .regDate(reviews.getRegDate())
        .modDate(reviews.getModDate())
        .build();
    return reviewDTO;
  }
}
