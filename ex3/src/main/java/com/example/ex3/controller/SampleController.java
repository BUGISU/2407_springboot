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