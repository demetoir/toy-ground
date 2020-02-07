package com.simple.rest_like_api.web;

import com.simple.rest_like_api.service.users.UsersService;
import com.simple.rest_like_api.web.dto.user.UsersListResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersSaveRequestDto;
import com.simple.rest_like_api.web.dto.user.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UsersApiController {
  private final UsersService usersService;

  @PostMapping("/api/v1/Users")
  public Long save(@RequestBody UsersSaveRequestDto requestDto) {
    return usersService.save(requestDto);
  }

  @PutMapping("/api/v1/Users/{id}")
  public Long updateById(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
    return usersService.updateById(id, requestDto);
  }

  @DeleteMapping("/api/v1/Users/{id}")
  public Long delete(@PathVariable Long id) {
    return usersService.deleteById(id);
  }

  @GetMapping("/api/v1/Users/{id}")
  public UsersResponseDto findById(@PathVariable Long id) {
    return usersService.findById(id);
  }

  @GetMapping("/api/v1/Users")
  public UsersListResponseDto findAll() {
    return usersService.findAll();
  }
}
