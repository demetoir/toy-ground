package com.simple.rest_like_api.service.books;

import com.simple.rest_like_api.domain.book.Books;
import com.simple.rest_like_api.domain.book.BooksRepository;
import com.simple.rest_like_api.web.dto.book.BooksListResponseDto;
import com.simple.rest_like_api.web.dto.book.BooksResponseDto;
import com.simple.rest_like_api.web.dto.book.BooksSaveRequestDto;
import com.simple.rest_like_api.web.dto.book.BooksUpdateRequestDto;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class BooksServiceTest {

  private BooksRepository repoMock;
  private BooksService service;
  private Books booksSpy;

  // @Before 에노테이션은 테스트 케이스를 개별로 실행하는경우 실행 되지 않음
  // 전체 클래스 단위 실행시만 실행이 된다
  @Before
  public void before() {
    System.out.println("before");
  }

  @BeforeEach
  public void beforeEach() {
    if (repoMock == null) {
      repoMock = Mockito.mock(BooksRepository.class);
      service = new BooksService(repoMock);
    }

    System.out.println("before each");
  }

  @Test
  public void able_to_load_test_resource() {
    System.out.println(repoMock);
    assertThat(repoMock).isNotNull();
  }

  @Test
  public void save() {
    // given
    String name = "1234";
    BooksSaveRequestDto dto = new BooksSaveRequestDto(name);
    Long expectedId = 1234L;
    Books bookSpy = spy(dto.toEntity());

    // when
    when(bookSpy.getId()).thenReturn(expectedId);
    when(repoMock.save(any(Books.class))).thenReturn(bookSpy);
    Long realId = service.save(dto);

    // than
    assertThat(realId).isEqualTo(expectedId);
    assertThat(bookSpy.getId()).isEqualTo(expectedId);
    assertThat(bookSpy.getName()).isEqualTo(name);
  }

  @Test
  public void updateById() {
    // given
    String name = "1234";
    BooksUpdateRequestDto dto = BooksUpdateRequestDto.builder().name(name).build();
    Long targetId = 1234L;
    Books booksSpy = spy(Books.builder().name("old").build());

    // when
    when(repoMock.findById(targetId)).thenReturn(Optional.of(booksSpy));
    Long realId = service.updateById(targetId, dto);

    // than
    assertThat(realId).isEqualTo(realId);
    assertThat(booksSpy.getName()).isEqualTo(name);
  }

  @Test
  public void deleteById() {
    // given
    Long targetId = 1234L;

    // when
    Long realId = service.deleteById(targetId);

    // than
    assertThat(realId).isEqualTo(targetId);
  }

  @Test
  public void findById() {

    // given
    String name = "name";
    Long targetId = 1234L;
    Books booksSpy = spy(Books.builder().name(name).build());

    // when
    when(booksSpy.getId()).thenReturn(targetId);
    when(repoMock.findById(targetId)).thenReturn(Optional.of(booksSpy));
    BooksResponseDto dto = service.findById(targetId);

    // than
    assertThat(dto).isNotNull();
    assertThat(dto.getId()).isEqualTo(targetId);
    assertThat(dto.getName()).isEqualTo(name);
  }

  @Test
  public void findAll() {
    // given
    String name = "name";
    Books booksSpy = spy(Books.builder().name(name).build());
    List<Books> booksList = Arrays.asList(booksSpy);

    // when
    when(repoMock.findAll()).thenReturn(booksList);
    BooksListResponseDto dto = service.findAll();

    // than
    assertThat(dto).isNotNull();
    assertThat(dto.getResponseDtoList()).isNotNull();
    assertThat(dto.getResponseDtoList().get(0)).isEqualToComparingFieldByField(booksSpy);
  }
}
