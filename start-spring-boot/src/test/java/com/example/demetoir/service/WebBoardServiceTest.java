package com.example.demetoir.service;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.dto.WebBoardDTO;
import com.example.demetoir.persistance.CustomCrudRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebBoardServiceTest {

  @Autowired private CustomCrudRepository repo;
  @Autowired private WebBoardService webBoardService;

  @Test
  public void save() {
    // given
    String title = "title";
    String writer = "writer";
    String content = "content";
    WebBoardDTO dto = WebBoardDTO.builder().title(title).writer(writer).content(content).build();

    // when
    Long id = webBoardService.save(dto);

    assertThat(id).isNotNull();
    assertThat(id).isGreaterThan(0L);

    // than
    Optional<WebBoard> res = repo.findById(id);
    assertThat(res.isPresent()).isEqualTo(true);

    WebBoard board = res.get();
    assertThat(board.getBno()).isEqualTo(id);
    assertThat(board.getTitle()).isEqualTo(title);
    assertThat(board.getContent()).isEqualTo(content);
    assertThat(board.getWriter()).isEqualTo(writer);
  }

  @Test
  public void findById() {
    // given
    String title = "title";
    String writer = "writer";
    String content = "content";
    WebBoardDTO targetDto =
        WebBoardDTO.builder().title(title).writer(writer).content(content).build();
    WebBoard target = repo.save(targetDto.toEntity());

    // when
    WebBoardDTO dto = webBoardService.findById(target.getBno());

    // than
    assertThat(dto).isNotNull();
    assertThat(dto.getBno()).isGreaterThan(0L);
    assertThat(dto.getContent()).isEqualTo(content);
    assertThat(dto.getTitle()).isEqualTo(title);
    assertThat(dto.getWriter()).isEqualTo(writer);
  }

  @Test
  public void updateById() {
    // given
    String title = "title";
    String writer = "writer";
    String content = "content";
    WebBoardDTO targetDto =
        WebBoardDTO.builder().title(title).writer(writer).content(content).build();
    WebBoard target = repo.save(targetDto.toEntity());

    String newTitle = "new title";
    String newContent = "new content";
    WebBoardDTO newDto = WebBoardDTO.builder().title(newTitle).content(newContent).build();

    // when
    Long id = webBoardService.updateById(target.getBno(), newDto);

    // than
    assertThat(id).isNotNull();
    assertThat(id).isEqualTo(target.getBno());

    Optional<WebBoard> res = repo.findById(target.getBno());
    assertThat(res.isPresent()).isEqualTo(true);

    WebBoard board = res.get();
    assertThat(board.getBno()).isEqualTo(id);
    assertThat(board.getContent()).isEqualTo(newContent);
    assertThat(board.getTitle()).isEqualTo(newTitle);
  }

  @Test
  public void deleteById() {
    // given
    String title = "title";
    String writer = "writer";
    String content = "content";
    WebBoardDTO targetDto =
        WebBoardDTO.builder().title(title).writer(writer).content(content).build();
    WebBoard target = repo.save(targetDto.toEntity());

    // when
    Long id = webBoardService.deleteById(target.getBno());

    // than
    assertThat(id).isNotNull();
    assertThat(id).isEqualTo(target.getBno());

    Optional<WebBoard> res = repo.findById(target.getBno());
    assertThat(res.isEmpty()).isEqualTo(true);
  }
}
