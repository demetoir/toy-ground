package com.simple.rest_like_api.web.dto.user;

import com.simple.rest_like_api.domain.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UsersListResponseDto {
  private List<UsersResponseDto> responseDtoList;

  public UsersListResponseDto(List<Users> usersList) {
    this.responseDtoList =
        usersList.stream().map(UsersResponseDto::new).collect(Collectors.toList());
  }
}
