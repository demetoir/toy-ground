package com.simple.rest_like_api.web.dto.user;

import com.simple.rest_like_api.domain.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersResponseDto {
  private Long id;
  private String name;

  public UsersResponseDto(Users entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }
}
