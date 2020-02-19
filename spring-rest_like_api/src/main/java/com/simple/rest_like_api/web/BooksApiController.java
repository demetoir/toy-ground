package com.simple.rest_like_api.web;

import com.simple.rest_like_api.service.books.BooksService;
import com.simple.rest_like_api.web.dto.book.BooksListResponseDto;
import com.simple.rest_like_api.web.dto.book.BooksResponseDto;
import com.simple.rest_like_api.web.dto.book.BooksSaveRequestDto;
import com.simple.rest_like_api.web.dto.book.BooksUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class BooksApiController {
  private final BooksService booksService;

  @PostMapping("/api/v1/books")
  public Long save(@RequestBody BooksSaveRequestDto requestDto) {
    return booksService.save(requestDto);
  }

  @PutMapping("/api/v1/books/{id}")
  public Long updateById(@PathVariable Long id, @RequestBody BooksUpdateRequestDto requestDto) {
  // todo exception handling
    return booksService.updateById(id, requestDto);
  }

  @DeleteMapping("/api/v1/books/{id}")
  public Long delete(@PathVariable Long id) {
  // todo exception handling
    return booksService.deleteById(id);
  }

  @GetMapping("/api/v1/books/{id}")
  public BooksResponseDto findById(@PathVariable Long id) {
    //404 https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
    try {
      return booksService.findById(id);
    } catch (EntityNotFoundException e) {
      throw new ResponseStatusException(NOT_FOUND, "Unable to find resource id: " + id);
    }
  }

  @GetMapping("/api/v1/books")
  public BooksListResponseDto findAll() {
    return booksService.findAll();
  }
}
