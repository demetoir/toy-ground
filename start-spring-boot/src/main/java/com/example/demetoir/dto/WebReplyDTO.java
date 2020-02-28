package com.example.demetoir.dto;

import com.example.demetoir.domain.WebReply;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WebReplyDTO {
  private Long rno;

  private String replyText;

  private String replyWriter;

  private Timestamp regDate;

  private Timestamp updatedDate;

  // 여기서 쓸대 없이 board 를 넣어서 reply 삭제시 board 가 직력화 하지못하는 에러가 발생함...
  //  private WebBoard board;

  @Builder
  public WebReplyDTO(
      Long rno, String replyText, String replyWriter, Timestamp regDate, Timestamp updatedDate) {
    this.rno = rno;
    this.replyText = replyText;
    this.replyWriter = replyWriter;
    this.regDate = regDate;
    this.updatedDate = updatedDate;
  }

  public WebReplyDTO(WebReply webReply) {
    this.rno = webReply.getRno();
    this.replyText = webReply.getReplyText();
    this.replyWriter = webReply.getReplyWriter();
    this.regDate = webReply.getRegDate();
    this.updatedDate = webReply.getUpdatedDate();
  }

  public WebReply toEntity() {
    return WebReply.builder().replyText(this.replyText).replyWriter(replyWriter).build();
  }
}
