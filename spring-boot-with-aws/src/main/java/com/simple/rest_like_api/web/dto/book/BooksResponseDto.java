package com.simple.rest_like_api.web.dto.book;

import com.simple.rest_like_api.domain.book.Books;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BooksResponseDto {
  private Long id;
  private String name;

  public BooksResponseDto(Books entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }
}
