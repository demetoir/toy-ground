package com.simple.rest_like_api.web.dto.book;

import com.simple.rest_like_api.domain.book.Books;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BooksSaveRequestDto {
  private String name;

  public Books toEntity() {
    return Books.builder().name(this.name).build();
  }

  @Builder
  public BooksSaveRequestDto(String name) {
    this.name = name;
  }
}
