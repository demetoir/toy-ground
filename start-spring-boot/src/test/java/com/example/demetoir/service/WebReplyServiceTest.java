package com.example.demetoir.service;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.domain.WebReply;
import com.example.demetoir.dto.WebBoardDTO;
import com.example.demetoir.dto.WebReplyDTO;
import com.example.demetoir.persistance.WebBoardRepository;
import com.example.demetoir.persistance.WebReplyRepository;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebReplyServiceTest {

  private WebReplyService webReplyService;
  @Autowired private WebReplyRepository webReplyRepository;
  @Autowired private WebBoardRepository webBoardRepository;

  @Before
  public void init() {
    this.webReplyService = new WebReplyService(webReplyRepository, webBoardRepository);
  }

  @Transactional
  @Test
  public void create() {
    // given
    String title = "title";
    String writer = "writer";
    String content = "content";
    WebBoardDTO webBoardDTO =
        WebBoardDTO.builder().title(title).writer(writer).content(content).build();

    WebBoard webBoard = webBoardRepository.save(webBoardDTO.toEntity());
    Long bno = webBoard.getBno();

    String replyText = "reply text";
    String replyWriter = "reply writer";
    WebReplyDTO replyDTO =
        WebReplyDTO.builder().replyText(replyText).replyWriter(replyWriter).build();

    // when
    Long rno = webReplyService.create(bno, replyDTO);

    // than
    assertThat(rno).isGreaterThan(0L);

    Optional<WebReply> realReplyOptional = webReplyRepository.findById(rno);
    assertThat(realReplyOptional.isPresent()).isTrue();

    WebReply realReply = realReplyOptional.get();
    assertThat(realReply.getReplyText()).isEqualTo(replyText);
    assertThat(realReply.getReplyWriter()).isEqualTo(replyWriter);

    Optional<WebBoard> realWebBoardOptional = webBoardRepository.findById(bno);
    assertThat(realWebBoardOptional.isPresent()).isTrue();

    WebBoard realWebBoard = realWebBoardOptional.get();
    assertThat(realWebBoard.getReplyList().get(0)).isEqualToComparingFieldByField(realReply);
  }

  @Transactional
  @Test
  public void updateById() {
    // given
    String title = "title";
    String writer = "writer";
    String content = "content";
    WebBoardDTO webBoardDTO =
        WebBoardDTO.builder().title(title).writer(writer).content(content).build();

    WebBoard webBoard = webBoardRepository.save(webBoardDTO.toEntity());
    Long bno = webBoard.getBno();

    String replyText = "reply text";
    String replyWriter = "reply writer";
    WebReplyDTO replyDTO =
        WebReplyDTO.builder().replyText(replyText).replyWriter(replyWriter).build();

    Long rno = webReplyService.create(bno, replyDTO);

    String newReplyText = "new reply text";
    WebReplyDTO newReplyDTO = WebReplyDTO.builder().replyText(newReplyText).build();

    // when
    Long resReplyId = webReplyService.updateById(rno, newReplyDTO);

    // than
    assertThat(resReplyId).isEqualTo(rno);

    Optional<WebReply> realReplyOptional = webReplyRepository.findById(rno);
    assertThat(realReplyOptional.isPresent()).isTrue();

    WebReply realReply = realReplyOptional.get();
    assertThat(realReply.getReplyText()).isEqualTo(newReplyText);

    Optional<WebBoard> realWebBoardOptional = webBoardRepository.findById(bno);
    assertThat(realWebBoardOptional.isPresent()).isTrue();

    WebBoard realWebBoard = realWebBoardOptional.get();
    assertThat(realWebBoard.getReplyList().get(0)).isEqualToComparingFieldByField(realReply);
  }

  @Transactional
  @Test
  public void deleteById() {
    // given
    String title = "title";
    String writer = "writer";
    String content = "content";
    WebBoardDTO webBoardDTO =
        WebBoardDTO.builder().title(title).writer(writer).content(content).build();

    WebBoard webBoard = webBoardRepository.save(webBoardDTO.toEntity());
    Long bno = webBoard.getBno();

    String replyText = "reply text";
    String replyWriter = "reply writer";
    WebReplyDTO replyDTO =
        WebReplyDTO.builder().replyText(replyText).replyWriter(replyWriter).build();

    Long rno = webReplyService.create(bno, replyDTO);

    // when
    Long resReplyId = webReplyService.deleteById(rno);

    // than
    assertThat(resReplyId).isEqualTo(rno);

    Optional<WebReply> realReply = webReplyRepository.findById(rno);
    assertThat(realReply.isEmpty()).isTrue();

    Optional<WebBoard> realWebBoardOptional = webBoardRepository.findById(bno);
    assertThat(realWebBoardOptional.isPresent()).isTrue();

    WebBoard realWebBoard = realWebBoardOptional.get();
    assertThat(realWebBoard.getReplyList().size()).isEqualTo(0);
  }

  @Transactional
  @Test
  public void findAllByBoardId() {
    // given
    String title = "title";
    String writer = "writer";
    String content = "content";
    WebBoardDTO webBoardDTO =
        WebBoardDTO.builder().title(title).writer(writer).content(content).build();

    WebBoard webBoard = webBoardRepository.save(webBoardDTO.toEntity());
    Long bno = webBoard.getBno();

    String replyText = "reply text";
    String replyWriter = "reply writer";
    WebReplyDTO replyDTO =
        WebReplyDTO.builder().replyText(replyText).replyWriter(replyWriter).build();

    Long rno = webReplyService.create(bno, replyDTO);

    // when
    List<WebReplyDTO> dtoList = webReplyService.findAllByBoardId(bno);

    // than
    assertThat(dtoList.size()).isEqualTo(1);

    WebReplyDTO dto = dtoList.get(0);
    assertThat(dto.getRno()).isEqualTo(rno);
    assertThat(dto.getReplyText()).isEqualTo(replyText);
    assertThat(dto.getReplyWriter()).isEqualTo(replyWriter);
  }
}
