package com.example.ex3.controller;

import com.example.ex3.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
  //template engine을 사용한 경우  thymeleaf를 적용한다면
  //resources 의 templates 폴더의 파일들을 사용해야하고,
  //이는 컨트롤러에서 반드시 중재해야만 페이지로 이동가능
  //컨트롤러에서 해당 주소에 대한 중재가 없다면 에러발생 404
  @GetMapping("/ex1")
  //void : 요청된 url 이 렌더링 주소와 같다
  public void ex1() {
    log.info("ex1...........");
  }
  @GetMapping("/selection")
  public void selection(Model model) {
    log.info("selection...........");
    SampleDTO sampleDTO = SampleDTO.builder()
        .sno(1L)
        .first("Park")
        .last("JS")
        .regTime(LocalDateTime.now())
        .build();
    model.addAttribute("sampleDTO", sampleDTO);
  }
}