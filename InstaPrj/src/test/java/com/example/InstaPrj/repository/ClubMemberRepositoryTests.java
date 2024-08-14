package com.example.InstaPrj.repository;

import com.example.InstaPrj.entity.ClubMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ClubMemberRepositoryTests {
    @Autowired
    ClubMemberRepository clubMemberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@a.a")
                    .password("1")
                    .name("Member" + i)
                    .build();
            clubMemberRepository.save(clubMember);
        });
    }
}