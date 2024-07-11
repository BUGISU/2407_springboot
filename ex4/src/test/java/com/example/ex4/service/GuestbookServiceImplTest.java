//src/test/java/com/example/ex4/service/GuestbookServiceImplTest.java
package com.example.ex4.service;
import com.example.ex4.dto.GuestbookDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookServiceImplTest {
  @Autowired
  private GuestbookService guestbookService;
  @Test
  void testRegister() {
    GuestbookDTO guestbookDTO = GuestbookDTO.builder()
        .title("new Title")
        .content("new Content")
        .writer("user1")
    .build();
    Long gno = guestbookService.register(guestbookDTO);
    System.out.println(gno);
  }
}
