//src/test/java/com/example/ex4/service/GuestbookServiceTests.java
package com.example.ex4.service;

import com.example.ex4.dto.GuestbookDTO;
import com.example.ex4.dto.PageRequestDTO;
import com.example.ex4.dto.PageResultDTO;
import com.example.ex4.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuestbookServiceTests {

  @Autowired
  private GuestbookService guestbookService;

  @Test
  void testRegister() {
    GuestbookDTO dto = GuestbookDTO.builder()
        .title("new Title")
        .content("new Content")
        .writer("user1")
        .build();
    Long gno = guestbookService.register(dto);
    System.out.println(gno);
  }

  @Test
  public void testList() {
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
        .page(1).size(10).build();
    PageResultDTO<GuestbookDTO, Guestbook> resultDTO =
        guestbookService.getList(pageRequestDTO);
    for (GuestbookDTO dto : resultDTO.getDtoList()) {
      System.out.println(dto);
    }
  }
}