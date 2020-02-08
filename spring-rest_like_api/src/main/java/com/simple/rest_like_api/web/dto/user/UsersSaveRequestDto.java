package com.simple.rest_like_api.web.dto.user;

import com.simple.rest_like_api.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersSaveRequestDto {
  private String name;

  public Users toEntity() {
    return Users.builder().name(this.name).build();
  }

  @Builder
  public UsersSaveRequestDto(String name) {
    this.name = name;
  }
}
