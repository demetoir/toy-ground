package com.simple.rest_like_api.web;

import com.simple.rest_like_api.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// method 1
// ref https://mkyong.com/spring-boot/spring-rest-integration-test-example/
// @SpringBootTest
// @AutoConfigureMockMvc
// method2
// ref https://meetup.toast.com/posts/124

@WebMvcTest(
    value = helloController.class,
    // helloController 의 경우 webMvcTest를 사용하기 떄문에 컴포넌트 스캔이 안되어 SecurityConfig 로드 되지 않음
    // 따라서 수동으로 추가해줌
    excludeFilters = {
      @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
    })
class helloControllerTest {

  @Autowired private MockMvc mvc;

  @Test
  @WithMockUser(roles = "USER")
  void hello() throws Exception {
    String hello = "hello";

    mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
  }

  @Test
  @WithMockUser(roles = "USER")
  void ableToReturn_helloDTo() throws Exception {
    String name = "this is name";
    int number = 42;

    mvc.perform(get("/hello/dto").param("name", name).param("number", String.valueOf(number)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.number", is(number)));
  }
}
