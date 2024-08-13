package com.example.InstaPrj.repository;

import com.example.InstaPrj.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository {
  // attributePaths에 정의된 속성은 eager로 패치하고, 나머지는 lazy 패치
  @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
  @Query("select m from Member m where m.email=:email ")
  Optional<Member> findByEmail(String email);

}
