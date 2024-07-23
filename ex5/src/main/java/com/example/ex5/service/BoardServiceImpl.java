package com.example.ex5.service;

import com.example.ex5.dto.BoardDTO;
import com.example.ex5.dto.PageRequestDTO;
import com.example.ex5.dto.PageResultDTO;
import com.example.ex5.entity.Board;
import com.example.ex5.entity.Member;
import com.example.ex5.repository.BoardRepository;
import com.example.ex5.repository.ReplyRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
  private final BoardRepository boardRepository;
  private final ReplyRepository replyRepository;
  @Override
  public Long register(BoardDTO boardDTO) {
    log.info("register....");
    Board board = dtdToEntity(boardDTO);
    boardRepository.save(dtdToEntity(boardDTO));
    return boardDTO.getBno();
  }

  @Override
  public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
    Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());
    Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
    Function<Object[], BoardDTO> fn = new Function<Object[], BoardDTO>() {
      @Override
      public BoardDTO apply(Object[] obj) {
        return entityToDto((Board) obj[0], (Member) obj[1], (Long) obj[2]);
      }
    };
    return new PageResultDTO<>(result, fn);
  }
/*  private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
    String type = pageRequestDTO.getType();
    String keyword = pageRequestDTO.getKeyword();
    // 동적 검색을 하기 위한 객체 생성
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QBoard qGuestbook = QGuestbook.guestbook; // 검색 대상인 QGuestbook
    BooleanExpression expression = qGuestbook.bno.gt(0L);//전체조건부여
    booleanBuilder.and(expression); // 첫번째 조건을 적용

    BooleanBuilder conditionBuilder = new BooleanBuilder(); // 세부 검색 조건 객체
    if(type == null || type.trim().length() == 0) return booleanBuilder;
    if(type.contains("t")) conditionBuilder.or(qGuestbook.title.contains(keyword));
    if(type.contains("c")) conditionBuilder.or(qGuestbook.content.contains(keyword));
    if(type.contains("w")) conditionBuilder.or(qGuestbook.writer.contains(keyword));
    booleanBuilder.and(conditionBuilder);
    return booleanBuilder;
  }*/
  @Override
  public BoardDTO get(Long bno) {
    Object result = boardRepository.getBoardByBno(bno);
    Object[] arr =(Object[]) result;
    return entityToDto((Board)arr[0], (Member) arr[1],(Long) arr[2]);
  }

  @Override
  public void modify(BoardDTO boardDTO) {
    Optional <Board> result = boardRepository.findById(boardDTO.getBno());
    if(result.isPresent()){
      Board board = result.get();
      board.changeTitle(boardDTO.getTitle());
      board.changeContent(boardDTO.getContent());
      boardRepository.save(board);
    }
  }

  @Transactional
  @Override
  public void removeWithReplies(Long bno) {
   replyRepository.deleteByBno(bno);
   boardRepository.deleteById(bno);
  }
}
