package com.simple.rest_like_api.domain.book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Books {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @Column(unique = true, length = 50)
  private String name;

  @Builder
  public Books(String name) {
    this.name = name;
  }

  public void update(String name) {
    this.name = name;
  }
}
