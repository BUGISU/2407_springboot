package com.example.InstaPrj.repository;

import com.example.InstaPrj.entity.ClubMember;
import com.example.InstaPrj.entity.Feeds;
import com.example.InstaPrj.entity.Reviews;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewsRepositoryTests {
    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    ClubMemberRepository clubMemberRepository;

    @Test
    public void insertReviews() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long fno = (long) (Math.random() * 100) + 1;
            Long cno = (long) (Math.random() * 100) + 1;
            Reviews review = Reviews.builder()
                    .clubMember(ClubMember.builder().cno(cno).build())
                    .feeds(Feeds.builder().fno(fno).build())
                    .grade((int) (Math.random() * 5) + 1)
                    .text("이 피드는.....")
                    .build();
            reviewsRepository.save(review);
        });
    }

    @Test
    public void testFindByFeeds() {
        List<Reviews> result = reviewsRepository.findByFeeds(
                Feeds.builder().fno(100L).build()
        );
        result.forEach(review -> {
            System.out.println(review.getReviewnum());
            System.out.println(review.getGrade());
            System.out.println(review.getText());
            System.out.println(review.getClubMember().getEmail());
            System.out.println();
        });
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteByClubMemeber() {
        Long cno = 1L;
        ClubMember clubMember = ClubMember.builder().cno(cno).build();
        reviewsRepository.deleteByMember(clubMember);
        clubMemberRepository.deleteById(cno);
    }
}
