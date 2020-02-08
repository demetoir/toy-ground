package com.simple.rest_like_api.service.users;

import com.simple.rest_like_api.domain.user.Users;
import com.simple.rest_like_api.domain.user.UsersRepository;
import com.simple.rest_like_api.web.dto.user.UsersListResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersSaveRequestDto;
import com.simple.rest_like_api.web.dto.user.UsersUpdateRequestDto;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UsersServiceTest {

  private UsersRepository repoMock;
  private UsersService service;
  private Users usersSpy;

  // @Before 에노테이션은 테스트 케이스를 개별로 실행하는경우 실행 되지 않음
  // 전체 클래스 단위 실행시만 실행이 된다
  @Before
  public void before() {
    System.out.println("before");
  }

  @BeforeEach
  public void beforeEach() {
    if (repoMock == null) {
      repoMock = Mockito.mock(UsersRepository.class);
      service = new UsersService(repoMock);
    }

    System.out.println("before each");
  }

  @Test
  public void able_to_load_test_resource() {
    System.out.println(repoMock);
    assertThat(repoMock).isNotNull();
  }

  @Test
  public void save() {
    // given
    String name = "1234";
    UsersSaveRequestDto dto = new UsersSaveRequestDto(name);
    Long expectedId = 1234L;
    Users userSpy = spy(dto.toEntity());

    // when
    when(userSpy.getId()).thenReturn(expectedId);
    when(repoMock.save(any(Users.class))).thenReturn(userSpy);
    Long realId = service.save(dto);

    // than
    assertThat(realId).isEqualTo(expectedId);
    assertThat(userSpy.getId()).isEqualTo(expectedId);
    assertThat(userSpy.getName()).isEqualTo(name);
  }

  @Test
  public void updateById() {
    // given
    String name = "1234";
    UsersUpdateRequestDto dto = UsersUpdateRequestDto.builder().name(name).build();
    Long targetId = 1234L;
    Users usersSpy = spy(Users.builder().name("old").build());

    // when
    when(repoMock.findById(targetId)).thenReturn(Optional.of(usersSpy));
    Long realId = service.updateById(targetId, dto);

    // than
    assertThat(realId).isEqualTo(realId);
    assertThat(usersSpy.getName()).isEqualTo(name);
  }

  @Test
  public void deleteById() {
    // given
    Long targetId = 1234L;

    // when
    Long realId = service.deleteById(targetId);

    // than
    assertThat(realId).isEqualTo(targetId);
  }

  @Test
  public void findById() {

    // given
    String name = "name";
    Long targetId = 1234L;
    Users usersSpy = spy(Users.builder().name(name).build());

    // when
    when(usersSpy.getId()).thenReturn(targetId);
    when(repoMock.findById(targetId)).thenReturn(Optional.of(usersSpy));
    UsersResponseDto dto = service.findById(targetId);

    // than
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isEqualTo(targetId);
    assertThat(dto.getName()).isEqualTo(name);
  }

  @Test
  public void findAll() {
    // given
    String name = "name";
    Users usersSpy = spy(Users.builder().name(name).build());
    List<Users> usersList = Arrays.asList(usersSpy);

    // when
    when(repoMock.findAll()).thenReturn(usersList);
    UsersListResponseDto dto = service.findAll();

    // than
    assertThat(dto).isNotNull();
    assertThat(dto.getResponseDtoList()).isNotNull();
    assertThat(dto.getResponseDtoList().get(0)).isEqualToComparingFieldByField(usersSpy);
  }
}
