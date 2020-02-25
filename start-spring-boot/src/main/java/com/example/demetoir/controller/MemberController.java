package com.example.demetoir.controller;

import com.example.demetoir.domain.Member;
import com.example.demetoir.persistance.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Log
@Controller
@RequestMapping("/member/")
public class MemberController {
//  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private PasswordEncoder encode;
  @Autowired private MemberRepository memberRepository;

  @GetMapping("/join")
  public void join() {}

  @Transactional
  @PostMapping("/join")
  public String joinPost(@ModelAttribute("member") Member member) {
    log.info("member : " + member);
    String envcrptpw = encode.encode(member.getUpw());

    log.info("member : " + envcrptpw);

    member.setUpw(envcrptpw);

    memberRepository.save(member);
    return "/member/joinResult";
  }
}
