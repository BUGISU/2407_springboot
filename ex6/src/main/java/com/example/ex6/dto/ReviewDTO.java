package com.example.ex6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
  private Long reviewnum;
  private Long mno; //Moive
  private Long mid; //Member
  private String nickname;
  private String email;
  private int grade; //별점
  private String text; //한줄평
  private LocalDateTime regDate, modDate;
}
