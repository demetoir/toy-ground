package com.example.demetoir.controller;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.persistance.CustomCrudRepository;
import com.example.demetoir.vo.PageMaker;
import com.example.demetoir.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// The job of @Controller is to create a Map of model object and find a view but @RestController
// simply return the object and object data is directly written into HTTP response as JSON or XML.
//
// Read more:
// https://javarevisited.blogspot.com/2017/08/difference-between-restcontroller-and-controller-annotations-spring-mvc-rest.html#ixzz6Ej4z0rTB
@Log
@Controller
public class WebBoardController {

  private CustomCrudRepository repo;

  @Autowired
  public WebBoardController(CustomCrudRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/boards/list")
  public void list(@ModelAttribute("pageVO") PageVO pageVO, Model model) {
    log.info("pagevo " + pageVO);
    Pageable page = pageVO.makePageable(0, "bno");
    log.info("page " + page);
    log.info("page " + page);

    Page<Object[]> result = repo.getCustomPage(pageVO.getType(), pageVO.getKeyword(), page);

    log.info("result " + result);
    log.info("total page number :" + result.getTotalPages());

    model.addAttribute("result", new PageMaker<>(result));
  }

  @GetMapping("/boards/register")
  public void registerGET(@ModelAttribute("vo") WebBoard vo) {
    log.info("register get");
  }

  @PostMapping("/boards/register")
  public String registerPOST(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {
    log.info("register post");
    log.info("" + vo);
    repo.save(vo);
    rttr.addFlashAttribute("msg", "success");
    return "redirect:/boards/list";
  }

  @GetMapping("/boards/view")
  public void view(long bno, @ModelAttribute("pageVO") PageVO pageVO, Model model) {
    log.info("bno " + bno);

    repo.findById(bno).ifPresent(webBoard -> model.addAttribute("vo", webBoard));
  }

  @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
  @GetMapping("/boards/modify")
  public void modifyGET(long bno, @ModelAttribute("pageVO") PageVO pageVO, Model model) {
    log.info("modify bno " + bno);

    repo.findById(bno).ifPresent(webBoard -> model.addAttribute("vo", webBoard));
  }

  @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
  @PostMapping("/boards/modify")
  public String modifyPost(WebBoard board, PageVO pageVO, RedirectAttributes rttr) {
    log.info("modify bn " + board.getBno());

    repo.findById(board.getBno())
        .ifPresent(
            origin -> {
              origin.setTitle(board.getTitle());
              origin.setContent(board.getContent());

              repo.save(origin);

              rttr.addFlashAttribute("msg", "success");
              rttr.addAttribute("bno", origin.getBno());
            });

    // 페이징과 검색했던 결과로 이동하는 경우
    rttr.addAttribute("page", pageVO.getPage());
    rttr.addAttribute("size", pageVO.getSize());
    rttr.addAttribute("type", pageVO.getType());
    rttr.addAttribute("keyword", pageVO.getKeyword());

    return "redirect:/boards/view";
  }

  @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
  @PostMapping("/boards/delete")
  public String delete(long bno, PageVO pageVO, RedirectAttributes rttr) {

    log.info("delete bno " + bno);
    repo.deleteById(bno);

    rttr.addFlashAttribute("msg", "success");
    rttr.addAttribute("page", pageVO.getPage());
    rttr.addAttribute("size", pageVO.getSize());
    rttr.addAttribute("type", pageVO.getType());
    rttr.addAttribute("keyword", pageVO.getKeyword());

    return "redirect:/boards/list";
  }

  @GetMapping("/sample")
  public void list(Model model) {
    model.addAttribute("hello", "hello");
  }
}
