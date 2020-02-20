package com.simple.rest_like_api.JPAAssociation.bidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString(exclude = "b")
public class AManyTOOne_Bi {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String data;

  @ManyToOne
  @JoinColumn
  private BManyToOne_Bi b;
}
