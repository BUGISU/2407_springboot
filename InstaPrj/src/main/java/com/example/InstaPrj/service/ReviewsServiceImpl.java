package com.example.InstaPrj.service;

import com.example.InstaPrj.dto.ReviewsDTO;
import com.example.InstaPrj.entity.Feeds;
import com.example.InstaPrj.entity.Reviews;
import com.example.InstaPrj.repository.ReviewsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {
  private final ReviewsRepository reviewsRepository;

  @Override
  public List<ReviewsDTO> getListOfFeeds(Long fno) {
    List<Reviews> result = reviewsRepository.findByFeeds(
        Feeds.builder().fno(fno).build());
    return result.stream().map(reviews -> entityToDto(reviews)).collect(Collectors.toList());
  }

  @Override
  public Long register(ReviewsDTO reviewsDTO) {
    log.info("reviewDTO >> ",reviewsDTO);
    Reviews review = dtoToEntity(reviewsDTO);
    reviewsRepository.save(review);
    return review.getReviewnum();
  }

  @Override
  public void modify(ReviewsDTO reviewDTO) {
    Optional<Reviews> result = reviewsRepository.findById(reviewDTO.getReviewnum());
    if (result.isPresent()) {
      Reviews review = result.get();
      review.changeGrade(reviewDTO.getGrade());
      review.changeText(reviewDTO.getText());
      reviewsRepository.save(review);
    }
  }

  @Override
  public void remove(Long reviewnum) {
    reviewsRepository.deleteById(reviewnum);
  }
}
