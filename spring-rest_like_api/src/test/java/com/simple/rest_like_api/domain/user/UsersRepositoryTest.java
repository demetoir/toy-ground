package com.simple.rest_like_api.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersRepositoryTest {
  @Autowired private UsersRepository usersRepository;

  private void init() {}

  @Test
  public void isTestAble() {
    assertThat(usersRepository).isNotNull();
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
