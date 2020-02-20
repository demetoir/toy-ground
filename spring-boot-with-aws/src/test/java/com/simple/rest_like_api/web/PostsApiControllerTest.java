package com.simple.rest_like_api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.rest_like_api.domain.post.Posts;
import com.simple.rest_like_api.domain.post.PostsRepository;
import com.simple.rest_like_api.web.dto.post.PostsSaveRequestDto;
import com.simple.rest_like_api.web.dto.post.PostsUpdateRequestDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

  @LocalServerPort private int port;

  @Autowired private PostsRepository postsRepository;

  private static final Log LOG = LogFactory.getLog(PostsApiControllerTest.class);

  @Autowired private WebApplicationContext context;

  private MockMvc mvc;
  private boolean didSetup = false;
  private String baseUrl;

  public void init() {
    mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    baseUrl = "http://localhost:" + port + "/api/v1/posts/";
  }

  @BeforeEach
  public void setup() {
    if (!didSetup) {
      init();
      didSetup = true;
    }
  }

  @AfterEach
  public void tearDown() {
    postsRepository.deleteAll();
  }

  @Test
  @WithMockUser(roles = "USER")
  public void able_to_save_post() throws Exception {
    // given
    String title = "title";
    String content = "content";
    PostsSaveRequestDto requestDto =
        PostsSaveRequestDto.builder().title(title).content(content).author("author").build();

    String url = "http://localhost:" + port + "/api/v1/posts";

    // when
    mvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
        .andExpect(status().isOk());

    // then
    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title);
    assertThat(all.get(0).getContent()).isEqualTo(content);
  }

  @Test
  @WithMockUser(roles = "USER")
  public void able_to_update_post() throws Exception {
    // given
    String title = "title";
    String content = "content";
    String author = "author";
    Posts savedPosts =
        postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

    Long updatedId = savedPosts.getId();
    String expectTitle = "title2";
    String expectContent = "content2";

    PostsUpdateRequestDto requestDto =
        PostsUpdateRequestDto.builder().title(expectTitle).content(expectContent).build();

    // when
    String url = this.baseUrl + updatedId;

    // when
    MvcResult mvcResult =
        mvc.perform(
                put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andReturn();

    // than
    String body = mvcResult.getResponse().getContentAsString();
    assertThat(Long.parseLong(body)).isGreaterThan(0L);

    Posts posts =
        postsRepository
            .findById(updatedId)
            .orElseThrow(() -> new Exception("업데이트 한 row 가 존재하지 않음"));

    assertThat(posts.getId()).isEqualTo(updatedId);
    assertThat(posts.getTitle()).isEqualTo(expectTitle);
    assertThat(posts.getContent()).isEqualTo(expectContent);
  }

  @Test
  @WithMockUser(roles = "USER")
  public void able_to_removeById() throws Exception {
    // given
    String title = "title";
    String content = "content";
    String author = "author";
    Posts savedPosts =
        postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

    Long removeId = savedPosts.getId();

    // when
    String url = this.baseUrl + removeId;
    MvcResult mvcResult = mvc.perform(delete(url)).andExpect(status().isOk()).andReturn();

    // than
    String body = mvcResult.getResponse().getContentAsString();
    assertThat(Long.parseLong(body)).isGreaterThan(0L);

    Optional<Posts> removedPost = postsRepository.findById(removeId);
    assertThat(removedPost.isPresent()).isFalse();
  }

  @Test
  @WithMockUser(roles = "USER")
  public void able_to_findById() throws Exception {
    // given
    String title = "title";
    String content = "content";
    String author = "author";

    Posts newPost =
        postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

    Long postId = newPost.getId();

    // when
    String url = this.baseUrl + postId;

    mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(postId))
        .andExpect(jsonPath("$.title").value(title))
        .andExpect(jsonPath("$.author").value(author))
        .andExpect(jsonPath("$.content").value(content));
  }

  @Test
  @WithMockUser(roles = "USER")
  public void able_to_findAll() throws Exception {
    // given
    String title = "title";
    String content = "content";
    String author = "author";
    postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

    // when
    String url = this.baseUrl;

    mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.postsLists.length()").value(1))
        .andExpect(jsonPath("$.postsLists[0].title").value(title))
        .andExpect(jsonPath("$.postsLists[0].author").value(author))
        .andExpect(jsonPath("$.postsLists[0].content").value(content))
        .andReturn();
  }
}
