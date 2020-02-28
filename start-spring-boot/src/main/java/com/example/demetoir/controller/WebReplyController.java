package com.example.demetoir.controller;

import com.example.demetoir.dto.WebBoardDTO;
import com.example.demetoir.dto.WebReplyDTO;
import com.example.demetoir.service.WebBoardService;
import com.example.demetoir.service.WebReplyService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/replies/*")
public class WebReplyController {

  private final WebReplyService webReplyService;
  private final WebBoardService webBoardService;

  public WebReplyController(WebReplyService webReplyService, WebBoardService webBoardService) {
    this.webReplyService = webReplyService;
    this.webBoardService = webBoardService;
  }

  // todo refactoring make some reply service
  // todo rest URL 형식이 적절하지 않은것 같다
  @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
  @PostMapping("/{bno}")
  public ResponseEntity<List<WebReplyDTO>> post(
      @PathVariable("bno") Long bno, @RequestBody WebReplyDTO replyDTO) {
    log.info(bno + " bno create reply " + replyDTO);

    webReplyService.create(bno, replyDTO);

    return new ResponseEntity<>(webReplyService.findAllByBoardId(bno), HttpStatus.CREATED);
  }

  @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
  @DeleteMapping("/{bno}/{rno}")
  public ResponseEntity<List<WebReplyDTO>> delete(
      @PathVariable("bno") Long bno, @PathVariable("rno") Long rno) {

    log.info("delete reply " + rno);

    webReplyService.deleteById(rno);

    return new ResponseEntity<>(webReplyService.findAllByBoardId(bno), HttpStatus.OK);
  }

  @Secured(value = {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
  @PutMapping("/{bno}")
  public ResponseEntity<List<WebReplyDTO>> put(
      @PathVariable("bno") Long bno, @RequestBody WebReplyDTO webReply) {
    log.info("update reply " + webReply.getRno());

    webReplyService.updateById(webReply.getRno(), webReply);

    return new ResponseEntity<>(webReplyService.findAllByBoardId(bno), HttpStatus.OK);
  }

  @GetMapping("/{bno}")
  public ResponseEntity<List<WebReplyDTO>> getReplies(@PathVariable("bno") Long bno) {

    WebBoardDTO dto = webBoardService.findById(bno);

    log.info("get replies of " + bno + " " + dto.getReplyList());

    return new ResponseEntity<>(webReplyService.findAllByBoardId(bno), HttpStatus.OK);
  }
}
