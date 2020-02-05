package com.simple.rest_like_api.service.posts;

import com.simple.rest_like_api.domain.post.Posts;
import com.simple.rest_like_api.domain.post.PostsRepository;
import com.simple.rest_like_api.web.dto.post.PostsResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// https://brunch.co.kr/@springboot/207
// pojo 아니라면 거의 다 붙이는드하다
@RunWith(MockitoJUnitRunner.class)
// 스프링 부트 테스트를 사용하지 안고 단위테스트로 구현함
// DI 할 been 인 repository를 주입하는대신 mock을 주입함
class PostsServiceTest {
  @Test
  void able_to_update_post() {
    String title = "1234";
    String author = "author";
    String content = "content";

    Optional<Posts> post =
        Optional.of(Posts.builder().author(author).content(content).title(title).build());

    PostsRepository repo = Mockito.mock(PostsRepository.class);
    Mockito.when(repo.findById(1L)).thenReturn(post);

    PostsService postsService = new PostsService(repo);
    PostsResponseDto responseDto = postsService.findById(1L);

    assertThat(responseDto).isNotNull();
    assertThat(responseDto.getContent()).isEqualTo(content);
    assertThat(responseDto.getTitle()).isEqualTo(title);
  }

  @Test
  void able_to_raise_while_update_post() {
    Optional<Posts> post = Optional.empty();

    PostsRepository repo = Mockito.mock(PostsRepository.class);
    Mockito.when(repo.findById(1L)).thenReturn(post);

    PostsService postsService = new PostsService(repo);

    // junit 5에서는 에노테이션을 사용하지않고 이렇게한다...
    assertThrows(IllegalArgumentException.class, () -> postsService.findById(1L));
  }

  @Test
  void save() {
    // todo
    assert false;
  }

  @Test
  void findById() {
    // todo
    assert false;
  }

  @Test
  void findAll() {
    // todo
    assert false;
  }

  @Test
  void deleteById() {
    // todo
    assert false;
  }
}
