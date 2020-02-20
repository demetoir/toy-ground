package com.simple.rest_like_api.domain.post;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.After;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class PostsRepositoryTest {

  @Autowired PostsRepository postsRepository;

  @After
  public void cleanup() {
    postsRepository.deleteAll();
  }

  @Test
  public void able_to_insert_Posts() {
    String title = "제목";
    String content = "본문";

    postsRepository.save(Posts.builder().title(title).content(content).author("sefsef").build());

    List<Posts> postsList = postsRepository.findAll();

    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);
  }
}
