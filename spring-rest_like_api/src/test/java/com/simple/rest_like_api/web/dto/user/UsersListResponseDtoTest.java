package com.simple.rest_like_api.web.dto.user;

import com.simple.rest_like_api.domain.user.Users;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UsersListResponseDtoTest {

  @Test
  public void able_to_construct() {
    // given
    String name = "13412";
    Users users = Users.builder().name("124124").build();
    List<Users> usersList = Arrays.asList(users, users);

    // when
    UsersListResponseDto dto = new UsersListResponseDto(usersList);

    // than
    assertThat(dto.getResponseDtoList().get(0)).isEqualToComparingFieldByField(users);
    assertThat(dto.getResponseDtoList().get(1)).isEqualToComparingFieldByField(users);
  }
}
