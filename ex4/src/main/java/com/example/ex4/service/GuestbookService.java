//src/main/java/com/example/ex4/service/GuestbookService.java
package com.example.ex4.service;

import com.example.ex4.dto.GuestbookDTO;
import com.example.ex4.dto.PageRequestDTO;
import com.example.ex4.dto.PageResultDTO;
import com.example.ex4.entity.Guestbook;

public interface GuestbookService {
  Long register(GuestbookDTO dto);

  PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO);

  default Guestbook dtoToEntity(GuestbookDTO dto){
   Guestbook guestbook = Guestbook.builder()
       .gno(dto.getGno())
       .title(dto.getTitle())
       .content(dto.getContent())
       .writer(dto.getWriter())
       .build();
   return guestbook;
  }
  default GuestbookDTO entityToDto(Guestbook gb){
    GuestbookDTO guestbookDTO = GuestbookDTO.builder()
        .gno(gb.getGno())
        .title(gb.getTitle())
        .content(gb.getContent())
        .writer(gb.getWriter())
        .regDate(gb.getRegDate())
        .modDate(gb.getModDate())
        .build();
    return guestbookDTO;
  }

}
