package com.simple.rest_like_api.domain.book;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class BooksRepositoryTest {
  @Autowired private BooksRepository booksRepository;

  private void init() {}

  @Test
  public void isTestAble() {
    assertThat(booksRepository).isNotNull();
  }

  @Test
  public void save() {
    // todo

    // given

    // than

    // when
    assert false;
  }
}
