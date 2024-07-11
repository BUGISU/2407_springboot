//src/main/java/com/example/ex4/dto/PageRequestDTO.java
package com.example.ex4.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO, EN> {
  //결과를 담기 위한 변수
  private List<DTO> dtoList;

  //생성자를 통해서 페이징된 result의 각각의 원소에 대한 함수 적용
  public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
    dtoList = result.stream().map(fn).collect(Collectors.toList());
  }
}
