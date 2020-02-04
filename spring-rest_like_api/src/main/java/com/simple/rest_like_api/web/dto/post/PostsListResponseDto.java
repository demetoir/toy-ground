package com.simple.rest_like_api.web.dto.post;

import com.simple.rest_like_api.domain.post.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostsListResponseDto {
  private List<PostsResponseDto> postsLists;

  public PostsListResponseDto(List<Posts> postsLists) {
    this.postsLists = postsLists.stream().map(PostsResponseDto::new).collect(Collectors.toList());
  }
}
