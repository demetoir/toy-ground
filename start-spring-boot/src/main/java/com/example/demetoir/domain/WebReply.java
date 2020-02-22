package com.example.demetoir.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@ToString(exclude = "board")
@Setter
@Table(name = "tbl_webreplies")
@EqualsAndHashCode(of = "rno")
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
}
