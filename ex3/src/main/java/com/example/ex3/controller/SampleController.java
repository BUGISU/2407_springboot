package com.example.ex3.controller;

import com.example.ex3.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {
  //template engine인 thymeleaf를 적용한다면
  //templates 폴더의 파일들을 사용해야 하고,
  //이는 컨트롤러에서 반드시 중재해야만 페이지로 이동가능
  //컨트롤러에서 해당주소에 대한 중재가 없다면 404에러 발생
  @GetMapping("/ex1")
  public void ex1() { //void:요청된 url이 렌더링주소와 같다
    log.info("ex1...........");
  }

  @GetMapping("/expression")
  public void selection(Model model) {
    log.info("selection...........");
    SampleDTO sampleDTO = SampleDTO.builder()
        .sno(1L)
        .first("Lee")
        .last("GH")
        .regTime(LocalDateTime.now())
        .build();
    // model은 다음페이지에 전달할 객체를 전송하는 역할
    model.addAttribute("sample", sampleDTO);
  }

  @GetMapping("/ex2")
  public void ex2(Model model) { //void:요청된 url이 렌더링주소와 같다
    log.info("ex2...........");
    List<SampleDTO> list = IntStream.rangeClosed(1, 20).asLongStream()
        .mapToObj(new LongFunction<SampleDTO>() {
          @Override
          public SampleDTO apply(long i) {
            SampleDTO dto = SampleDTO.builder()
                .sno(i).first("First..." + i).last("Last..." + i)
                .regTime(LocalDateTime.now()).build();
            return dto;
          }
        }).collect(Collectors.toList());
    model.addAttribute("list", list);
  }

  @GetMapping("/exInline")
  public String exInline(Model model, RedirectAttributes ra) { //void:요청된 url이 렌더링주소와 같다
    log.info("exInline...........");
    SampleDTO dto = SampleDTO.builder()
        .sno(100L).first("First...100" ).last("Last...100")
        .regTime(LocalDateTime.now()).build();
    model.addAttribute("dtoModel", dto.toString());
    ra.addAttribute("dtoRA",dto.toString());
    ra.addFlashAttribute("dtoFlash",dto.toString());
    ra.addFlashAttribute("result","success");
    return "redirect:/sample/ex3";
  }
  @GetMapping("/ex3")
  public String ex3(Model model, RedirectAttributes ra) { //void:요청된 url이 렌더링주소와 같다
    log.info("exInline...........");
    SampleDTO dto = SampleDTO.builder()
        .sno(100L).first("First...100" ).last("Last...100")
        .regTime(LocalDateTime.now()).build();
    model.addAttribute("dtoModel1", dto.toString());
    ra.addAttribute("dtoRA1",dto.toString());
    ra.addFlashAttribute("dtoFlash1",dto.toString());
    ra.addFlashAttribute("result1","success");
    return "/sample/ex3";
  }
}
