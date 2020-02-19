package com.simple.rest_like_api.service.books;

import com.simple.rest_like_api.domain.book.Books;
import com.simple.rest_like_api.domain.book.BooksRepository;
import com.simple.rest_like_api.web.dto.book.BooksSaveRequestDto;
import com.simple.rest_like_api.web.dto.book.BooksListResponseDto;
import com.simple.rest_like_api.web.dto.book.BooksResponseDto;
import com.simple.rest_like_api.web.dto.book.BooksUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BooksService {
  private final BooksRepository booksRepository;

  @Transactional
  public Long save(BooksSaveRequestDto requestDto) {
    return booksRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public Long updateById(Long id, BooksUpdateRequestDto requestDto) {
    Books books =
        booksRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("id : " + id + "is not exist in books"));

    books.update(requestDto.getName());
    // @transactional 에노테이션을 안붙이면 제대로 업데이트 안됨
    // 잘되려면 아래에 추가해야함
    // 서비스 테스트시 mocking을 사용해서 잡지 못한 버그...
    // usersRepository.save(users)

    return id;
  }

  @Transactional
  public Long deleteById(Long id) {
    booksRepository.deleteById(id);

    return id;
  }

  public BooksResponseDto findById(Long id) {
    Books user =
        booksRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("id : " + id + "is not exist in books"));

    return new BooksResponseDto(user);
  }

  public BooksListResponseDto findAll() {
    List<Books> booksList = booksRepository.findAll();

    return new BooksListResponseDto(booksList);
  }
}
