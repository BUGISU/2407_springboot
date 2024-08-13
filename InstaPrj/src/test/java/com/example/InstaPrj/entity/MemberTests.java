package com.example.InstaPrj.entity;

import com.example.InstaPrj.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberTests {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void insertDummies() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member member = Member.builder()
          .email("user" + i + "@a.a")
          .nickname("사용자"+i)
          .fromSocial(false)
          .password(passwordEncoder.encode("1"))
          .build();
      member.addMemberRole(MemberRole.USER);
      memberRepository.save(member);
    });
  }

  @Test
  public void testRead() {
    Optional<Member> result = memberRepository.findByEmail("user100@a.a");
    if (result.isPresent()) System.out.println(result.get());
  }
}