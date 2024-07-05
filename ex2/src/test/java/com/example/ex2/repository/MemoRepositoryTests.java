package com.example.ex2.repository;

import com.example.ex2.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTests {
  @Autowired
  MemoRepository memoRepository;

  @Test
  public void testClass() {
    System.out.println(">>" + memoRepository.getClass().getName());
  }

  @Test
  public void testInsertDummies() {
//  rangeClosed 마지막 까지 포함함
    IntStream.rangeClosed(1, 100).forEach(new IntConsumer() {
      @Override
      public void accept(int value) {
        Memo memo = Memo.builder()
            .memoText("Simple memo..." + value)
            .build();
        memoRepository.save(memo);
      }
    });
  }

  @Test
  public void testSelect() {
    Long mno = 100L;
    Optional<Memo> result = memoRepository.findById(mno);
    if (result.isPresent()) {
      Memo memo = result.get();
      System.out.println(memo);
    }
  }

  @Test
  public void testUpdate() {
    //주의 요함(column 이 2개 밖에 없어서 mno, memoText 만 수정,많을 경우는 먼저 불러와야함
    Memo memo = Memo.builder().mno(100L).memoText("update 100").build();
    memoRepository.save(memo);

    Long mno = 100L;
    Optional<Memo> result = memoRepository.findById(mno);
    if (result.isPresent()) {
      Memo memo3 = Memo.builder().memoText("update 100").build();
      memoRepository.save(memo3);
    }
  }

  @Test
  public void testDelete() {
    Long mno = 100L;
    memoRepository.deleteById(mno);
  }

  @Test
  public void testPageDefault() {
    //페이지를 지정할 수 있는 객체
    Pageable pageable = PageRequest.of(0, 10);
    Page<Memo> result = memoRepository.findAll(pageable);
    System.out.println(result);
    System.out.println("========================================");
    System.out.println("Total Page : " + result.getTotalPages());
    System.out.println("Total Count : " + result.getTotalElements());
    System.out.println("Page Number : " + result.getNumber());
    System.out.println("Page Size : " + result.getSize());
    System.out.println("has next Page : " + result.hasNext());
    System.out.println("has Previous Page : " + result.hasPrevious());
    System.out.println("first Page : " + result.isFirst());
    System.out.println("last Page : " + result.isLast());

    Sort sort = Sort.by("mno").descending();
    pageable = PageRequest.of(0, 10, sort);
    result = memoRepository.findAll(pageable);
    for (Memo memo : result.getContent()) {
      System.out.println(memo);
    }
  }
  @Test
  public void testQueryMethod(){
    List<Memo> list = memoRepository.findByMemoTextContaining("7");
    for(Memo memo : list){
      System.out.println(memo);
    }
  }
  @Test
  public void testQueryMethodPagealbe(){
    Pageable pageable
        = PageRequest.of(0,10, Sort.by("mno").descending());
    Page<Memo> result = memoRepository.findByMnoBetween(10L,50L,pageable);
    result.get().forEach(memo -> System.out.println(memo));

  }
  //쿼리메서드에 deleteBy 가 있는 경우 검색후 삭제이기 떄문에 @Commit @Test 를 붙여줘야함
  @Transactional
  @Commit
  @Test
  public void testDeleteQueryMethod(){
    memoRepository.deleteMemoByMnoLessThan(10L); //10보다 작은수 다 지움
  }
}