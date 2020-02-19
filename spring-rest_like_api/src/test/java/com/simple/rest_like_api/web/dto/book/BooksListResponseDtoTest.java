package com.simple.rest_like_api.web.dto.book;

import com.simple.rest_like_api.domain.book.Books;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BooksListResponseDtoTest {

  @Test
  public void able_to_construct() {
    // given
    String name = "13412";
    Books books = Books.builder().name("124124").build();
    List<Books> booksList = Arrays.asList(books, books);

    // when
    BooksListResponseDto dto = new BooksListResponseDto(booksList);

    // than
    assertThat(dto.getResponseDtoList().get(0)).isEqualToComparingFieldByField(books);
    assertThat(dto.getResponseDtoList().get(1)).isEqualToComparingFieldByField(books);
  }
}
