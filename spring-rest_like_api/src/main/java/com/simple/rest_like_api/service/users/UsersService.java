package com.simple.rest_like_api.service.users;

import com.simple.rest_like_api.domain.user.Users;
import com.simple.rest_like_api.domain.user.UsersRepository;
import com.simple.rest_like_api.web.dto.user.UsersSaveRequestDto;
import com.simple.rest_like_api.web.dto.user.UsersListResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {
  private final UsersRepository usersRepository;

  @Transactional
  public Long save(UsersSaveRequestDto requestDto) {
    return usersRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public Long updateById(Long id, UsersUpdateRequestDto requestDto) {
    Users users =
        usersRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("id : " + id + "is not exist in Users"));

    users.update(requestDto.getName());
    // @transactional 에노테이션을 안붙이면 제대로 업데이트 안됨
    // 잘되려면 아래에 추가해야함
    // 서비스 테스트시 mocking을 사용해서 잡지 못한 버그...
    // usersRepository.save(users)

    return id;
  }

  @Transactional
  public Long deleteById(Long id) {
    usersRepository.deleteById(id);

    return id;
  }

  public UsersResponseDto findById(Long id) {
    Users user =
        usersRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("id : " + id + "is not exist in Users"));

    return new UsersResponseDto(user);
  }

  public UsersListResponseDto findAll() {
    List<Users> usersList = usersRepository.findAll();

    return new UsersListResponseDto(usersList);
  }
}
