package com.simple.rest_like_api.JPAAssociation.unidirectional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
// JPA 테스트용 빈만 주입하도록 하면
// SprintBootTest 보다  빠르게 테스트 가능
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ManyToOne_UnidirectionalTest {
  @Autowired
  AManyToOneRepo aRepo;

  @Autowired
  BManyToOneRepo bRepo;

  private final String bData = "<B data>";
  private final String aData = "<A data>";

  @Test
  public void able_to_setup() {
    assertThat(aRepo).isNotNull();
    assertThat(bRepo).isNotNull();
  }

  @Test
  public void able_to_create() {

    BManyToOne b = new BManyToOne();
    b.setData(bData);

    bRepo.save(b);

    AManyToOne a = new AManyToOne();
    a.setData(aData);
    a.setB(b);

    a = aRepo.save(a);

    System.out.println(aRepo.findById(a.getId()).get());

    System.out.println(aRepo.findAll());
    System.out.println(bRepo.findAll());
  }
}
