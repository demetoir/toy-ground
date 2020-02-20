package com.simple.rest_like_api.web.dto.book;

import com.simple.rest_like_api.domain.book.Books;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BooksListResponseDto {
  private List<BooksResponseDto> responseDtoList;

  public BooksListResponseDto(List<Books> booksList) {
    this.responseDtoList =
        booksList.stream().map(BooksResponseDto::new).collect(Collectors.toList());
  }
}
