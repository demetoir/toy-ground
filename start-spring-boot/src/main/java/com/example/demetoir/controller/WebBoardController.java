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
public class WebBoardController {

  private WebBoardRepository repo;

  @Autowired
  public WebBoardController(WebBoardRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/boards/list")
  public void list(PageVO pageVO, Model model) {

    Pageable page = pageVO.makePageable(0, "bno");
    Page<WebBoard> result = repo.findAll(repo.makePredicate(pageVO.getType(), pageVO.getKeyword()), page);


    log.info("page " + page);
    log.info("result " + result);

    log.info("total page number :" + result.getTotalPages());

    PageMaker<WebBoard> resultPage = new PageMaker<>(result);
    log.info("" + resultPage.getPrevPage());
    model.addAttribute("result", resultPage);
  }

  @GetMapping("/sample")
  public void list(Model model) {
    model.addAttribute("hello", "hello");
  }
}
