package com.simple.rest_like_api.JPAExample.ManyToOne.Unidiretion;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ManyToOne_UnidirectionTest {
  @Autowired ARepo aRepo;

  @Autowired BRepo bRepo;

  private final String bData = "<B data>";
  private final String aData = "<A data>";

  @Test
  public void able_to_setup() {
    assertThat(aRepo).isNotNull();
    assertThat(bRepo).isNotNull();
  }

  @Test
  public void able_to_() {

    B b = new B();
    b.setData(bData);

    bRepo.save(b);

    A a = new A();
    a.setData(aData);
    a.setB(b);

    a = aRepo.save(a);

    System.out.println(aRepo.findById(a.getId()).get());

    System.out.println(aRepo.findAll());
    System.out.println(bRepo.findAll());
  }
}
