package com.example.demetoir.controller;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.domain.WebReply;
import com.example.demetoir.persistance.WebReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
@RequestMapping("/replies/*")
public class WebReplyController {

  @Autowired private WebReplyRepository webReplyRepository;

  // todo refactoring make some reply service
  // todo rest URL 형식이 적절하지 않은것 같다
  @Transactional
  @PostMapping("/{bno}")
  public ResponseEntity<List<WebReply>> post(
      @PathVariable("bno") Long bno, @RequestBody WebReply webReply) {
    log.info("create reply");
    log.info("bno " + bno);
    log.info("reply " + webReply);

    WebBoard board = new WebBoard();
    board.setBno(bno);

    webReply.setBoard(board);
    webReplyRepository.save(webReply);

    return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
  }

  @Transactional
  @DeleteMapping("/{bno}/{rno}")
  public ResponseEntity<List<WebReply>> delete(
      @PathVariable("bno") Long bno, @PathVariable("rno") Long rno) {

    log.info("delete reply " + rno);

    webReplyRepository.deleteById(rno);
    WebBoard board = new WebBoard();
    board.setBno(bno);

    return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
  }

  @Transactional
  @PutMapping("/{bno}")
  public ResponseEntity<List<WebReply>> put(
      @PathVariable("bno") Long bno, @RequestBody WebReply webReply) {
    log.info("update reply " + bno);

    webReplyRepository
        .findById(webReply.getRno())
        .ifPresent(
            origin -> {
              origin.setReplyText(webReply.getReplyText());

              webReplyRepository.save(origin);
            });

    WebBoard board = new WebBoard();
    board.setBno(bno);

    return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
  }

  @GetMapping("/{bno}")
  public ResponseEntity<List<WebReply>> getReplies(@PathVariable("bno") Long bno) {

    log.info("get replies + " + bno);

    WebBoard board = new WebBoard();
    board.setBno(bno);

    return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
  }

  private List<WebReply> getListByBoard(WebBoard board) {
    log.info("get list of board " + board);

    return webReplyRepository.getWebRepliesOfBoard(board);
  }
}
