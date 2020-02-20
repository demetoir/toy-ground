package com.simple.rest_like_api.web;

import com.simple.rest_like_api.config.auth.LoginUser;
import com.simple.rest_like_api.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IndexController {

  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user) {
    if (user != null) {
      return "logined as" + user.getName();
    }

    return "not logined";
  }
}
