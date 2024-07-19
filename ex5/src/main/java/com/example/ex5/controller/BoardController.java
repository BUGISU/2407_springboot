package com.example.ex5.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/board")

public class BoardController {
  @RequestMapping({"", "", "/list"})
  public String list() {
    return "/board/list";
  }
}
