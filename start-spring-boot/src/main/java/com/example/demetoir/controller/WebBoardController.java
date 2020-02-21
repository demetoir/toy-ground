package com.example.demetoir.controller;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.persistance.WebBoardRepository;
import com.example.demetoir.vo.PageMaker;
import com.example.demetoir.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller
// @RequestMapping("/boards")
public class WebBoardController {

  private WebBoardRepository repo;

  @Autowired
  public WebBoardController(WebBoardRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/boards/list")
  public void list(PageVO vo, Model model) {

    Pageable page = vo.makePageable(0, "bno");
    Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);

    log.info("page " + page);
    log.info("result " + result);

    log.info("total page number :" + result.getTotalPages());
    model.addAttribute("result", new PageMaker<>(result));
  }

  @GetMapping("/sample")
  public void list(Model model) {
    model.addAttribute("hello", "hello");
  }
}
