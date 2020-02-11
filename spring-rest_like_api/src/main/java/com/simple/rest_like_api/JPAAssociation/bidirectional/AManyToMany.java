package com.simple.rest_like_api.JPAAssociation.bidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "bList")
@Entity
public class AManyToMany {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String data;

  @ManyToMany(cascade = CascadeType.ALL)
  private List<BManyToMany> bList = new ArrayList<>();
}
