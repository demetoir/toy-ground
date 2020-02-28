package com.example.demetoir.service;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.domain.WebReply;
import com.example.demetoir.dto.WebReplyDTO;
import com.example.demetoir.persistance.WebBoardRepository;
import com.example.demetoir.persistance.WebReplyRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class WebReplyService {

  private final WebReplyRepository webReplyRepository;
  private final WebBoardRepository webBoardRepository;

  public WebReplyService(
      WebReplyRepository webReplyRepository, WebBoardRepository webBoardRepository) {
    this.webReplyRepository = webReplyRepository;
    this.webBoardRepository = webBoardRepository;
  }

  @Transactional
  public Long create(Long bno, WebReplyDTO dto) {
    WebReply reply = dto.toEntity();
    WebBoard board =
        webBoardRepository
            .findById(bno)
            .orElseThrow(
                () -> new IllegalArgumentException("board of bno :" + bno + " can not find"));

    board.getReplyList().add(reply);
    reply.setBoard(board);

    return webReplyRepository.save(reply).getRno();
  }

  @Transactional
  public Long updateById(long id, WebReplyDTO dto) {
    WebReply webReply =
        webReplyRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id " + id + " dose not exist"));

    webReply.update(dto.getReplyText());
    webReplyRepository.save(webReply);

    return id;
  }

  @Transactional
  public Long deleteById(long id) {
    WebReply webReply =
        webReplyRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id " + id + " dose not exist"));

    WebBoard board = webReply.getBoard();
    board.getReplyList().remove(webReply);

    webReplyRepository.deleteById(id);

    return id;
  }

  public List<WebReplyDTO> findAllByBoardId(Long id) {
    WebBoard board = new WebBoard();
    board.setBno(id);

    log.info(webReplyRepository.getWebRepliesOfBoard(board).toString());
    return webReplyRepository.getWebRepliesOfBoard(board).stream()
        .map(WebReplyDTO::new)
        .collect(Collectors.toList());
  }
}
