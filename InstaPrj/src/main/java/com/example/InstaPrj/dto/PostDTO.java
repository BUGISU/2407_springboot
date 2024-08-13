package com.example.InstaPrj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
  private Long pmo; //pst 번호
  private String content; //게시글 내용
  @Builder.Default
  private List<PostImageDTO> postImageDTOList = new ArrayList<>();
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
