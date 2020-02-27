package com.example.demetoir.dto;

import com.example.demetoir.domain.WebBoard;
import com.example.demetoir.domain.WebReply;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class WebBoardDTO {
  private Long bno;

  private String title;

  private String writer;

  private String content;

  private Timestamp regDate;

  private Timestamp updatedDate;

  private List<WebReply> replyList;

  @Builder
  public WebBoardDTO(
      Long bno,
      String title,
      String writer,
      String content,
      Timestamp regDate,
      Timestamp updatedDate,
      List<WebReply> replyList) {
    this.bno = bno;
    this.title = title;
    this.writer = writer;
    this.content = content;
    this.regDate = regDate;
    this.updatedDate = updatedDate;
    this.replyList = replyList;
  }

  public WebBoardDTO(WebBoard webBoard) {
    this.bno = webBoard.getBno();
    this.title = webBoard.getTitle();
    this.writer = webBoard.getWriter();
    this.content = webBoard.getContent();
    this.regDate = webBoard.getRegDate();
    this.updatedDate = webBoard.getUpdatedDate();
    this.replyList = webBoard.getReplyList();
  }

  public WebBoard toEntity() {
    return WebBoard.builder().bno(bno).content(content).title(title).writer(writer).build();
  }
}
