package com.simple.rest_like_api.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersUpdateRequestDto {
  private String name;

  @Builder
  public UsersUpdateRequestDto(String name) {
    this.name = name;
  }
}
