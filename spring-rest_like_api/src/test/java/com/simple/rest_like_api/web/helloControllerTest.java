package com.simple.rest_like_api.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest
class helloControllerTest {

  @Autowired private MockMvc mvc;

  @Test
  void hello() throws Exception {
    String hello = "hello";

    mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
  }

  @Test
  void hello2() throws Exception {
    String hello = "hello";

    mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
  }

  @Test
  void ableToReturn_helloDTo() throws Exception {
    String name = "this is name";
    int number = 42;

    mvc.perform(get("/hello/dto").param("name", name).param("number", String.valueOf(number)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.number", is(number)));
  }
}
