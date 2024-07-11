//src/main/java/com/example/ex4/dto/PageRequestDTO.java
package com.example.ex4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDTO {
  private int page; //요청되는 page 번호
  private int size; //페이지당 목록의 갯수

  public PageRequestDTO() {
    page =1;
    size =10;
  }
  //Pageable은 페이지 처리를 위한 객체
  public Pageable getPageable(Sort sort){
    //page -1 :: page 는 0번부터 시작한다
    return PageRequest.of(page -1, size, sort);
  }
}
