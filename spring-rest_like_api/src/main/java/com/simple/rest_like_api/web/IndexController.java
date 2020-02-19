package com.simple.rest_like_api.web;


import com.simple.rest_like_api.config.auth.dto.SessionUser;
import com.simple.rest_like_api.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class IndexController {

  private final HttpSession httpSession;

  @GetMapping("/")
  public String index(Model model) {
    SessionUser user = (SessionUser) httpSession.getAttribute("user");

    if (user != null) {

      return "logined as" + user.getName();
    }
    return "not logined";
  }
}
