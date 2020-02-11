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
@ToString(exclude = "aList")
@Entity
public class BManyToMany {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String data;

  @ManyToMany(mappedBy = "bList")
  private List<AManyToMany> aList = new ArrayList<>();
}
