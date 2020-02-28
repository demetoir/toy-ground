package com.example.demetoir.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@ToString(exclude = "board")
@Setter
@Table(name = "tbl_webreplies")
@EqualsAndHashCode(of = "rno")
@NoArgsConstructor
@Entity
public class WebReply {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rno;

  private String replyText;

  private String replyWriter;

  @CreationTimestamp private Timestamp regDate;

  @UpdateTimestamp private Timestamp updatedDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private WebBoard board;

  @Builder
  public WebReply(Long rno, String replyText, String replyWriter, WebBoard webBoard) {
    this.rno = rno;
    this.replyText = replyText;
    this.replyWriter = replyWriter;
    this.board = webBoard;
  }

  public void update(String replyText) {
    this.replyText = replyText;
  }
}
