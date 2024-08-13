package com.example.InstaPrj.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
  public void exAll() {log.info("/all");
  } //모든 이 접근


}
