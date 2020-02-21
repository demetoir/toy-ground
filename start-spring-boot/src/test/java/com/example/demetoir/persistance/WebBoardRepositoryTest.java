package com.example.demetoir.persistance;

import com.example.demetoir.domain.WebBoard;
import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
class WebBoardRepositoryTest {
  @Autowired private WebBoardRepository repo;

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  public void insertDummyData() {
    IntStream.range(0, 100)
        .forEach(
            i -> {
              WebBoard board = new WebBoard();

              board.setTitle("title " + i);
              board.setContent("content " + i);
              board.setWriter("writer " + i);
              repo.save(board);
            });
  }
}
