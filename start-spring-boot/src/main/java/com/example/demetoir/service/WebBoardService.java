package com.example.demetoir.service;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.dto.WebBoardDTO;
import com.example.demetoir.persistance.CustomCrudRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log
@Service
public class WebBoardService {
  @Autowired private CustomCrudRepository repo;

  public WebBoardDTO findById(Long id) {
    WebBoard board =
        repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id " + id + " dose not exist"));

    return new WebBoardDTO(board);
  }

  @Transactional
  public Long save(WebBoardDTO dto) {
    return repo.save(dto.toEntity()).getBno();
  }

  @Transactional
  public Long updateById(Long id, WebBoardDTO dto) {
    WebBoard board =
        repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id " + id + " dose not exist"));

    board.update(dto.getTitle(), dto.getContent());

    repo.save(board);

    return id;
  }

  @Transactional
  public Long deleteById(Long id) {
    WebBoard board =
        repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("id " + id + " dose not exist"));

    repo.delete(board);

    return id;
  }
}
