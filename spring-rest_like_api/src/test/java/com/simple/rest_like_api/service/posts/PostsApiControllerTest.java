package com.simple.rest_like_api.service.posts;

import com.simple.rest_like_api.domain.post.Posts;
import com.simple.rest_like_api.domain.post.PostsRepository;
import com.simple.rest_like_api.web.dto.post.PostsSaveRequestDto;

import com.simple.rest_like_api.web.dto.post.PostsUpdateRequestDto;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Autowired private PostsRepository postsRepository;

  @Test
  public void able_to_submit_post() {
    String title = "title";
    String content = "content";
    PostsSaveRequestDto requestDto =
        PostsSaveRequestDto.builder().title(title).content(content).author("author").build();

    String url = "http://localhost:" + port + "/api/v1/posts";

    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title);
    assertThat(all.get(0).getContent()).isEqualTo(content);
  }

  @Test
  public void able_to_update_post() {
    Posts savedPosts =
        postsRepository.save(
            Posts.builder().title("tsetse").content("content").author("aut" + "").build());

    Long updatedId = savedPosts.getId();
    String expectTitle = "title2";
    String expectContent = "content2";

    PostsUpdateRequestDto requestDto =
        PostsUpdateRequestDto.builder().title(expectTitle).content(expectContent).build();

    String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

    HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

    ResponseEntity<Long> responseEntity =
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(expectTitle);
    assertThat(all.get(0).getContent()).isEqualTo(expectContent);
  }
}
