package com.simple.rest_like_api.JPAAssociation.unidirectional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
// JPA 테스트용 빈만 주입하도록 하면
// SprintBootTest 보다  빠르게 테스트 가능
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OneToMany_UnidirectionalTest {
  @Autowired AOneToManyReo aRepo;

  @Autowired BOneToManyReo bRepo;

  private final String bData = "<B data>";
  private final String aData = "<A data>";

  @Test
  public void able_to_setup() {
    assertThat(aRepo).isNotNull();
    assertThat(bRepo).isNotNull();
  }

  @Test
  public void able_to_create() {

    // cascade 옵션 주지않으면 b를 매번 저장해야함
    // 한번에 연산하도록 하는게 더 좋음...
    //  @OneToMany(cascade = CascadeType.ALL)

    BOneToMany b1 = new BOneToMany();
    b1.setData("b1");
//    bRepo.save(b1);

    BOneToMany b2 = new BOneToMany();
    b2.setData("b2");
//    bRepo.save(b2);

    AOneToMany a = new AOneToMany();
    a.setData(aData);
    a.setBList(Arrays.asList(b1, b2));

    a = aRepo.save(a);

    System.out.println(aRepo.findById(a.getId()).get());

    System.out.println(aRepo.findAll());
    System.out.println(bRepo.findAll());
  }
}
