package com.simple.rest_like_api.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @Column(unique = true, length = 50)
  private String name;

  @Builder
  public Users(String name) {
    this.name = name;
  }

  public void update(String name) {
    this.name = name;
  }
}
