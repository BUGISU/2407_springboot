//src/main/java/com/example/ex4/service/GuestbookServiceImpl.java
package com.example.ex4.service;

import com.example.ex4.dto.GuestbookDTO;
import com.example.ex4.entity.Guestbook;
import com.example.ex4.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

//service 구현체
@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{
  private final GuestbookRepository guestbookRepository;
  @Override
  public Long register(GuestbookDTO dto) {
    Guestbook guestbook = dtoToEntity(dto);
    guestbookRepository.save(guestbook);
    return guestbook.getGno();
  }
}