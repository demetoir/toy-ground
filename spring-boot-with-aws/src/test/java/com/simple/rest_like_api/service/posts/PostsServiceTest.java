package com.simple.rest_like_api.service.posts;

import com.simple.rest_like_api.domain.post.Posts;
import com.simple.rest_like_api.domain.post.PostsRepository;
import com.simple.rest_like_api.web.dto.post.PostsListResponseDto;
import com.simple.rest_like_api.web.dto.post.PostsResponseDto;
import com.simple.rest_like_api.web.dto.post.PostsSaveRequestDto;
import com.simple.rest_like_api.web.dto.post.PostsUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

// https://brunch.co.kr/@springboot/207
// pojo 아니라면 거의 다 붙이는드하다
@RunWith(MockitoJUnitRunner.class)
// 스프링 부트 테스트를 사용하지 안고 단위테스트로 구현함
// DI 할 been 인 repository를 주입하는대신 mock을 주입함
// spring boot test 보다 빠르게 테스트 할 수 있다.
class PostsServiceTest {
  @Test
  void able_to_findById() {
    String title = "1234";
    String author = "author";
    String content = "content";
    Long id = 1L;

    Optional<Posts> post =
        Optional.of(Posts.builder().author(author).content(content).title(title).build());

    PostsRepository repo = Mockito.mock(PostsRepository.class);
    when(repo.findById(id)).thenReturn(post);

    PostsService postsService = new PostsService(repo);
    PostsResponseDto responseDto = postsService.findById(id);

    assertThat(responseDto).isNotNull();
    assertThat(responseDto.getContent()).isEqualTo(content);
    assertThat(responseDto.getTitle()).isEqualTo(title);
  }

  @Test
  void able_to_raise_while_findById() {
    Optional<Posts> post = Optional.empty();

    PostsRepository repo = Mockito.mock(PostsRepository.class);
    Long postId = 1L;
    when(repo.findById(postId)).thenReturn(post);

    PostsService postsService = new PostsService(repo);

    // junit 5에서는 에노테이션을 사용하지않고 이렇게한다...
    assertThrows(IllegalArgumentException.class, () -> postsService.findById(postId));
  }

  @Test
  void able_to_save() {
    String title = "1234";
    String author = "author";
    String content = "content";
    Long expectedId = 1L;

    PostsSaveRequestDto saveRequestDto =
        PostsSaveRequestDto.builder().author(author).content(content).title(title).build();
    Posts post = saveRequestDto.toEntity();

    // Poost의 id는 자동으로 설정되는 값임
    // @Setter를 붙여 setId로 하는것은 oop스럽지 못하다.
    // Posts.getId 메서드만 모킹이 필요한 경우이다.
    // 따라서 mock의 spy를 이용하면 Posts 클래스를 수정하지 않고 가능하다.
    Posts spyPost = spy(post);
    when(spyPost.getId()).thenReturn(expectedId);

    // entity 인 post 는 맴버 변수인 id 가   null 이므로 getId 하다러아ㅗ null이 나오므로 테스트가 힘들다.
    // entity 를 목킹하는 방법을 먼저 찾음
    // save 시 id 에 대한 assertion 을 하지 못한다
    PostsRepository repo = Mockito.mock(PostsRepository.class);
    // 직접 Posts 나 saveRequestDto.toEntity() 를 인자로 주었을때 테스트시 mock 된 repo 에서 null 에러를 발생 시킴
    // 그래서 any Post.class 들어왔을때 반환하도록 목을 만듬
    when(repo.save(any(Posts.class))).thenReturn(spyPost);

    PostsService postsService = new PostsService(repo);
    Long newId = postsService.save(saveRequestDto);

    assertThat(newId).isGreaterThan(0L);
  }

  @Test
  void able_to_update() {
    // given
    String title = "1234";
    String author = "author";
    String content = "content";
    Long updateId = 431L;

    PostsUpdateRequestDto updateRequestDto =
        PostsUpdateRequestDto.builder().content(content).title(title).build();

    Posts post = Posts.builder().title("old").author(author).content("old").build();
    Posts spyPost = spy(post);
    when(spyPost.getId()).thenReturn(updateId);

    PostsRepository repo = Mockito.mock(PostsRepository.class);
    when(repo.findById(any(Long.class))).thenReturn(Optional.of(spyPost));

    PostsService postsService = new PostsService(repo);

    // when
    Long newId = postsService.update(updateId, updateRequestDto);

    // than
    assertThat(newId).isEqualTo(updateId);
    assertThat(spyPost.getTitle()).isEqualTo(title);
    assertThat(spyPost.getContent()).isEqualTo(content);
    assertThat(spyPost.getAuthor()).isEqualTo(author);
  }

  @Test
  void able_to_deleteById() {
    // given
    String title = "1234";
    String author = "author";
    String content = "content";
    Long targetId = 431L;

    Posts post = Posts.builder().title(title).author(author).content(content).build();
    Posts spyPost = spy(post);
    when(spyPost.getId()).thenReturn(targetId);

    PostsRepository repo = Mockito.mock(PostsRepository.class);
    when(repo.findById(any(Long.class))).thenReturn(Optional.of(spyPost));

    PostsService postsService = new PostsService(repo);

    // when
    Long deletedId = postsService.deleteById(targetId);

    // than
    assertThat(deletedId).isEqualTo(targetId);
  }

  @Test
  void able_to_findAll() {
    // given
    String title = "1234";
    String author = "author";
    String content = "content";

    Posts post = Posts.builder().title(title).author(author).content(content).build();
    List<Posts> postsList = Arrays.asList(post, post);
    PostsListResponseDto expectedDto = new PostsListResponseDto(postsList);

    PostsRepository repo = Mockito.mock(PostsRepository.class);
    when(repo.findAll()).thenReturn(postsList);

    PostsService postsService = new PostsService(repo);

    // when
    PostsListResponseDto realDto = postsService.findAll();

    // than
    // 객체 비교가 어쨰서 안되는가????
    // 일단 string 비교로 했지만
    // Objects.deepEqual dksxhdgka
    System.out.println(realDto);
    System.out.println(expectedDto);
    System.out.println(Objects.deepEquals(expectedDto, realDto));
    System.out.println(expectedDto.equals(realDto));
    System.out.println(expectedDto == realDto);
    assertThat(realDto.toString()).isEqualTo(expectedDto.toString());
  }
}
