package com.example.test_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductsDTO {
  private Long pno; //제품번호
  private String pname;//제품명
  private String pmodel; //모델명
  private String pmaker; //제조자
  private int price; //가격
  private LocalDateTime regDate, modDate; //등록일자, 수정일자

}
