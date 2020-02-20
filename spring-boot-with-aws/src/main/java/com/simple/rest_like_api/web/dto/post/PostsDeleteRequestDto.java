package com.simple.rest_like_api.web.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsDeleteRequestDto {
  private Long id;

  @Builder
  public PostsDeleteRequestDto(Long id) {
    this.id = id;
  }
}
