package com.example.demetoir.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_webboards")
@EqualsAndHashCode(of = "bno")
@NoArgsConstructor
@ToString
public class WebBoard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;

  private String title;

  private String writer;

  private String content;

  @CreationTimestamp private Timestamp regDate;

  @UpdateTimestamp private Timestamp updatedDate;

  @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<WebReply> replyList = new ArrayList<>();

  @Builder
  public WebBoard(Long bno, String title, String writer, String content) {
    this.bno = bno;
    this.writer = writer;
    this.title = title;
    this.content = content;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
