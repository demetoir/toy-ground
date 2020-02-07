package com.simple.rest_like_api.service.users;

import com.simple.rest_like_api.domain.user.Users;
import com.simple.rest_like_api.domain.user.UsersRepository;
import com.simple.rest_like_api.web.dto.user.UsersSaveRequestDto;
import com.simple.rest_like_api.web.dto.user.UsersListResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {
  private final UsersRepository usersRepository;

  public Long save(UsersSaveRequestDto requestDto) {
    return usersRepository.save(requestDto.toEntity()).getId();
  }

  public Long updateById(Long id, UsersUpdateRequestDto requestDto) {
    Users users =
        usersRepository
            .findById(id)
            .orElseThrow(
                () -> new IllegalArgumentException("id : " + id + "is not exist in Users"));

    users.update(requestDto.getName());

    return id;
  }

  public Long deleteById(Long id) {
    usersRepository.deleteById(id);

    return id;
  }

  public UsersResponseDto findById(Long id) {
    Users user =
        usersRepository
            .findById(id)
            .orElseThrow(
                () -> new IllegalArgumentException("id : " + id + "is not exist in Users"));

    return new UsersResponseDto(user);
  }

  public UsersListResponseDto findAll() {
    List<Users> usersList = usersRepository.findAll();

    return new UsersListResponseDto(usersList);
  }
}
