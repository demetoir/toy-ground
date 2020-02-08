package com.simple.rest_like_api.web;

import com.simple.rest_like_api.domain.user.Users;
import com.simple.rest_like_api.domain.user.UsersRepository;
import com.simple.rest_like_api.service.users.UsersService;
import com.simple.rest_like_api.web.dto.user.UsersListResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersResponseDto;
import com.simple.rest_like_api.web.dto.user.UsersSaveRequestDto;
import com.simple.rest_like_api.web.dto.user.UsersUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersApiControllerTest {
  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private UsersRepository usersRepository;

  @Autowired private UsersService usersService;

  private String urlHead;

  @BeforeEach
  public void beforeEach() {
    urlHead = "http://localhost:" + port + "/api/v1/users";
  }

  @Test
  public void is_test_able() {
    assertThat(urlHead).isNotNull();
    assertThat(port).isNotNull();
    assertThat(restTemplate).isNotNull();
    assertThat(usersRepository).isNotNull();
    assertThat(usersService).isNotNull();
  }

  @Test
  public void able_to_save() throws Exception {
    // given
    String url = urlHead;
    String name = "name";
    UsersSaveRequestDto dto = UsersSaveRequestDto.builder().name(name).build();

    // when
    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    Long resultId = responseEntity.getBody();
    assertThat(resultId).isGreaterThan(0L);
    assertThat(resultId).isNotNull();

    Users users =
        usersRepository
            .findById(resultId)
            .orElseThrow(() -> new Exception("able to find saved user"));

    assertThat(users).isNotNull();
    assertThat(users.getId()).isEqualTo(resultId);
    assertThat(users.getName()).isEqualTo(name);
  }

  @Test
  public void able_to_updateById() throws Exception {
    // given
    // setup old value
    String oldName = "old";
    UsersSaveRequestDto oldDto = UsersSaveRequestDto.builder().name(oldName).build();
    Users oldUsers = usersRepository.save(oldDto.toEntity());
    Long targetId = oldUsers.getId();

    // setup new value
    String url = urlHead + "/" + targetId;
    String newName = "new";
    UsersUpdateRequestDto newDto = UsersUpdateRequestDto.builder().name(newName).build();

    // when
    // testresttamplate 에서는 put 메서드에는 리턴이 없음
    // 그래서 이렇게 HttpEntity로 wrapping 한 객체를 넘겨주는 방식으로 해야함
    HttpEntity<UsersUpdateRequestDto> httpEntity = new HttpEntity<>(newDto);
    ResponseEntity<Long> responseEntity =
        restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Long.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    Long resultId = responseEntity.getBody();
    assertThat(resultId).isNotNull();
    assertThat(resultId).isGreaterThan(0L);
    assertThat(resultId).isEqualTo(targetId);

    Users users =
        usersRepository
            .findById(resultId)
            .orElseThrow(() -> new Exception("able to find updated user"));
    assertThat(users).isNotNull();
    assertThat(users.getId()).isEqualTo(resultId);
    assertThat(users.getName()).isEqualTo(newName);
  }

  @Test
  public void removeById() {
    // given
    // setup old value
    String name = "delete";
    UsersSaveRequestDto oldDto = UsersSaveRequestDto.builder().name(name).build();
    Users oldUsers = usersRepository.save(oldDto.toEntity());
    Long targetId = oldUsers.getId();

    // setup new value
    String url = urlHead + "/" + targetId;

    // when
    HttpEntity<Long> httpEntity = new HttpEntity<>(targetId);
    ResponseEntity<Long> responseEntity =
        restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Long.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    Long resultId = responseEntity.getBody();
    assertThat(resultId).isNotNull();
    assertThat(resultId).isGreaterThan(0L);
    assertThat(resultId).isEqualTo(targetId);

    assertThrows(
        Exception.class,
        () ->
            usersRepository
                .findById(resultId)
                .orElseThrow(() -> new Exception("able to find updated user")));
  }

  @Test
  public void able_to_findAll() {
    // given
    // setup old value
    String name = "plane";
    UsersSaveRequestDto oldDto = UsersSaveRequestDto.builder().name(name).build();
    Users oldUsers = usersRepository.save(oldDto.toEntity());

    // setup new value
    String url = urlHead;

    // when
    ResponseEntity<UsersListResponseDto> responseEntity =
        restTemplate.getForEntity(url, UsersListResponseDto.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    UsersListResponseDto dto = responseEntity.getBody();
    assertThat(dto).isNotNull();
    assertThat(dto.getResponseDtoList()).isNotNull();
    assertThat(dto.getResponseDtoList()).isNotEmpty();
  }

  @Test
  public void able_to_findById() {
    // given
    // setup old value
    String name = "plane";
    UsersSaveRequestDto oldDto = UsersSaveRequestDto.builder().name(name).build();
    Users oldUsers = usersRepository.save(oldDto.toEntity());
    Long targetId = oldUsers.getId();

    // setup new value
    String url = urlHead + "/" + targetId;

    // when
    ResponseEntity<UsersResponseDto> responseEntity =
        restTemplate.getForEntity(url, UsersResponseDto.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    UsersResponseDto dto = responseEntity.getBody();
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isEqualTo(targetId);
    assertThat(dto.getName()).isEqualTo(name);
  }

  @Test
  public void try_findById_but_not_Exist() {
    // given
    // setup old value
    usersRepository.deleteAll();
    Long targetId = 1235L;

    // setup new value
    String url = urlHead + "/" + targetId;

    // when
    ResponseEntity<UsersResponseDto> responseEntity =
        restTemplate.getForEntity(url, UsersResponseDto.class);

    System.out.println(responseEntity.getStatusCode());

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
