package com.simple.rest_like_api.web.dto.post;

import com.simple.rest_like_api.domain.post.Posts;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostsListResponseDtoTest {
  @Test
  void able_to_construct() {
    Posts posts = Posts.builder().title("123").author("124").content("23523").build();
    List<Posts> postsList = new ArrayList<>();
    postsList.add(posts);
    postsList.add(posts);
    postsList.add(posts);

    PostsListResponseDto postsListResponseDto = new PostsListResponseDto(postsList);
    System.out.println(postsListResponseDto.toString());
  }
}
