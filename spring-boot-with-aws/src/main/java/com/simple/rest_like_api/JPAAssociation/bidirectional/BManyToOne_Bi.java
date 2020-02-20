package com.simple.rest_like_api.JPAAssociation.bidirectional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString(exclude = "aList")
public class BManyToOne_Bi {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String data;

  @OneToMany(mappedBy = "b", cascade = CascadeType.ALL  )
  private List<AManyTOOne_Bi> aList;
}
