package com.simple.rest_like_api.web.dto.user;

import com.simple.rest_like_api.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UsersUpdateRequestDto {
  private String name;

  @Builder
  public UsersUpdateRequestDto(String name) {
    this.name = name;
  }

  public Users toEntity() {
    return Users.builder().name(this.name).build();
  }
}
