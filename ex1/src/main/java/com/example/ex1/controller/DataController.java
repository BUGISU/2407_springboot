package com.example.ex1.controller;

import com.example.ex1.dto.Foo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {
  @GetMapping("/foo")
  public String getFoo(){
    return new Foo().toString();
  }
}
