package com.example.ex2.repository;

import com.example.ex2.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

  // 쿼리메서드(Query Method): 메서드이름자체가 질의문이다.
  List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
  List<Memo> findByMemoTextContaining(String search);
  //  List<Memo> findByMemoTextNotLike(String search);

  Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

  void deleteMemoByMnoLessThan(Long num);

  //@Query은 쿼리메서드로는 해결되지 않는 경우 직접 쿼리를 작성
  @Query("select m from Memo m order by m.mno desc")
  List<Memo> getListDesc();

  //매개변수를 쿼리에 각각 전달할 때
  @Transactional
  @Modifying
  @Query("update Memo m set m.memoText = :memoText where m.mno = :mno ")
  int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);

  //매개변수를 쿼리에 객체로 전달할 때
  @Transactional
  @Modifying
  @Query(
      "update Memo m set m.memoText = :#{#param.memoText} " +
          "where m.mno = :#{#param.mno} ")
  int updateMemoText(@Param("param") Memo memo);
}