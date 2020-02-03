package com.simple.rest_like_api.web;

import com.simple.rest_like_api.web.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("hello/dto")
  public HelloResponseDTO helloDTo(
      @RequestParam("name") String name, @RequestParam("number") int number) {

    return new HelloResponseDTO(name, number);
  }
}
