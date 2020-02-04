package com.simple.rest_like_api.service.posts;

import com.simple.rest_like_api.domain.post.Posts;
import com.simple.rest_like_api.domain.post.PostsRepository;
import com.simple.rest_like_api.web.dto.post.*;

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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private PostsRepository postsRepository;

  @Test
  public void able_to_save_post() throws Exception {
    String title = "title";
    String content = "content";
    PostsSaveRequestDto requestDto =
        PostsSaveRequestDto.builder().title(title).content(content).author("author").build();

    String url = "http://localhost:" + port + "/api/v1/posts";

    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);
    Long postId = responseEntity.getBody();

    Posts post = postsRepository.findById(postId).orElseThrow(() -> new Exception(" 한 row를 찾지 못함"));
    assertThat(post.getTitle()).isEqualTo(title);
    assertThat(post.getContent()).isEqualTo(content);
  }

  @Test
  public void able_to_update_post() throws Exception {
    // given
    Posts savedPosts =
        postsRepository.save(
            Posts.builder().title("tsetse").content("content").author("aut" + "").build());

    Long updatedId = savedPosts.getId();
    String expectTitle = "title2";
    String expectContent = "content2";

    PostsUpdateRequestDto requestDto =
        PostsUpdateRequestDto.builder().title(expectTitle).content(expectContent).build();

    // when
    String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;
    HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
    ResponseEntity<Long> responseEntity =
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    Posts posts =
        postsRepository
            .findById(updatedId)
            .orElseThrow(() -> new Exception("업데이트 한 row 가 존재하지 않음"));

    assertThat(posts.getTitle()).isEqualTo(expectTitle);
    assertThat(posts.getContent()).isEqualTo(expectContent);
  }

  @Test
  public void able_to_removeById() {
    // given
    Posts savedPosts =
        postsRepository.save(
            Posts.builder().title("tsetse").content("content").author("aut" + "").build());

    Long removeId = savedPosts.getId();
    PostsDeleteRequestDto requestDto = PostsDeleteRequestDto.builder().id(0L).build();

    // when
    String url = "http://localhost:" + port + "/api/v1/posts/" + removeId;
    HttpEntity<PostsDeleteRequestDto> requestEntity = new HttpEntity<>(requestDto);
    ResponseEntity<Long> responseEntity =
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    Optional<Posts> removedPost = postsRepository.findById(removeId);
    assertThat(removedPost.isPresent()).isFalse();
  }

  @Test
  public void able_to_findById() {
    // given

    String title = "titile";
    String content = "contnet";
    String author = "auther";

    Posts newPost =
        postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

    Long postId = newPost.getId();

    // when
    String url = "http://localhost:" + port + "/api/v1/posts/" + postId;
    ResponseEntity<PostsResponseDto> responseEntity =
        restTemplate.getForEntity(url, PostsResponseDto.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().getId()).isEqualTo(postId);
    assertThat(responseEntity.getBody().getTitle()).isEqualTo(title);
    assertThat(responseEntity.getBody().getAuthor()).isEqualTo(author);
    assertThat(responseEntity.getBody().getContent()).isEqualTo(content);
  }

  @Test
  public void able_to_findAll() {
    // given
    Posts newPost =
        postsRepository.save(
            Posts.builder().title("tsetse").content("content").author("aut" + "").build());

    Long postId = newPost.getId();
    PostsDeleteRequestDto requestDto = PostsDeleteRequestDto.builder().id(0L).build();

    // when
    String url = "http://localhost:" + port + "/api/v1/posts";
    HttpEntity<PostsDeleteRequestDto> requestEntity = new HttpEntity<>(requestDto);
    ResponseEntity<PostsListResponseDto> responseEntity =
        restTemplate.getForEntity(url, PostsListResponseDto.class);

    // than
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
