package com.simple.rest_like_api.web;

import com.simple.rest_like_api.service.users.UsersService;
import com.simple.rest_like_api.web.dto.user.UsersListResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersSaveRequestDto;
import com.simple.rest_like_api.web.dto.user.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class UsersApiController {
  private final UsersService usersService;

  @PostMapping("/api/v1/users")
  public Long save(@RequestBody UsersSaveRequestDto requestDto) {
    return usersService.save(requestDto);
  }

  @PutMapping("/api/v1/users/{id}")
  public Long updateById(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
  // todo exception handling
    return usersService.updateById(id, requestDto);
  }

  @DeleteMapping("/api/v1/users/{id}")
  public Long delete(@PathVariable Long id) {
  // todo exception handling
    return usersService.deleteById(id);
  }

  @GetMapping("/api/v1/users/{id}")
  public UsersResponseDto findById(@PathVariable Long id) {
    //404 https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
    try {
      return usersService.findById(id);
    } catch (EntityNotFoundException e) {
      throw new ResponseStatusException(NOT_FOUND, "Unable to find resource id: " + id);
    }
  }

  @GetMapping("/api/v1/users")
  public UsersListResponseDto findAll() {
    return usersService.findAll();
  }
}
