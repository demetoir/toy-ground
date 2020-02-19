package com.simple.rest_like_api.web.dto.book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BooksUpdateRequestDto {
  private String name;

  @Builder
  public BooksUpdateRequestDto(String name) {
    this.name = name;
  }
}
