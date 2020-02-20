package com.simple.rest_like_api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController2 {

  @GetMapping("/hello2")
  public String hello() {
    return "hello2";
  }
}
