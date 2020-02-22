package com.example.demetoir.persistance;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.domain.WebReply;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebReplyRepositoryTest {

  @Autowired private WebReplyRepository repo;

  @Test
  public void testInsertReplies() {
    Long[] arr = {300L, 303L, 301L};

    Arrays.stream(arr)
        .forEach(
            num -> {
              WebBoard board = new WebBoard();
              board.setBno(num);

              IntStream.range(0, 10)
                  .forEach(
                      i -> {
                        WebReply reply = new WebReply();
                        reply.setReplyText("reply text" + i);
                        reply.setReplyWriter("reply writer " + i);
                        reply.setBoard(board);

                        repo.save(reply);
                      });
            });
  }
}
