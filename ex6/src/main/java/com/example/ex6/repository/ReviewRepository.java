package com.example.ex6.repository;

import com.example.ex6.entity.Movie;
import com.example.ex6.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  //findByMovie 할 때는 연결해서 사용해 달라
  @EntityGraph(attributePaths = {"member"},
      type = EntityGraph.EntityGraphType.FETCH)
  List<Review> findByMovie(Movie movie);
}
