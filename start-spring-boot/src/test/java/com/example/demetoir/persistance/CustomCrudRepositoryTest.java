package com.example.demetoir.persistance;

import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
class CustomCrudRepositoryTest {

  @Autowired private CustomCrudRepository repo;

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void test1() {
    Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

    String type = "t";
    String keyword = "10";

    Page<Object[]> result = repo.getCustomPage(type, keyword, pageable);

    log.info("" + result);
    log.info("total page " + result.getTotalPages());
    log.info("size " + result.getSize());

    result
        .getContent()
        .forEach(
            arr -> {
              log.info(Arrays.toString(arr));
            });

    log.info("" + result);
  }
}
