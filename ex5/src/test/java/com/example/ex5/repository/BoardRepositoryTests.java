package com.example.ex5.repository;

import com.example.ex5.entity.Board;
import com.example.ex5.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTests {
  @Autowired
  BoardRepository boardRepository;

  @Test
  public void innsertBoards() {

    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member member = Member.builder().email("user" + i + "@a.a").build();
      Board board = Board.builder()
          .title("Title..." + i)
          .content("Content..." + i)
          .writer(member)
          .build();
      boardRepository.save(board);
    });
  }

  @Transactional
  @Test
  public void testRead1() {
    Optional<Board> result = boardRepository.findById(100L);
    if (result.isPresent()) {
      Board board = result.get();
      System.out.println(">>" + board);
      System.out.println(">>" + board.getWriter());
    }
  }

  @Test
  public void testReadWithWriter() {
    //Optional<T>은 선언된 Entity가 있을 경우 사용,
    // Board, Member 를 담을 수 있는 객체를 굳이 별도로 선언하지 않고 단순히 Object 로 받음
    Object result = boardRepository.getBoardWithWriter(100L);
    Object[] arr = (Object[]) result;
    System.out.println(Arrays.toString(arr));
  }
}