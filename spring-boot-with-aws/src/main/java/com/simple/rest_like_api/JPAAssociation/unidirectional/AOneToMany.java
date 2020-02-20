package com.simple.rest_like_api.JPAAssociation.unidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class AOneToMany {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String data;

  @OneToMany(cascade = CascadeType.ALL)
  //oneToMany 시 join column 안하면 중간 테이블 하나 더 생김
  @JoinColumn
  private List<BOneToMany> bList;
}
