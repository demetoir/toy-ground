package com.simple.rest_like_api.JPAAssociation.bidirectional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ManyToMany_Test {
  @Autowired private AManyToManyRepo aRepo;
  @Autowired private BManyToManyRepo bRepo;

  @Test
  public void able_to() {
    BManyToMany b1 = new BManyToMany();
    b1.setData("bbb");
    b1.setAList(new ArrayList<>());
    bRepo.save(b1);

    BManyToMany b2 = new BManyToMany();
    b2.setData("bbb");
    b2.setAList(new ArrayList<>());
    bRepo.save(b2);

    AManyToMany a1 = new AManyToMany();
    a1.setData("aaa");
    a1.getBList().add(b1);
    aRepo.save(a1);

    AManyToMany a2 = new AManyToMany();
    a2.setData("aaa");
    a2.getBList().add(b2);
    aRepo.save(a2);

//    b1.getAList().add(a1);
//    bRepo.save(b1);
//
//    b2.getAList().add(a2);
//    bRepo.save(b2);

    System.out.println(aRepo.findAll());
    System.out.println(aRepo.findAll().get(0).getBList());
    System.out.println(aRepo.findAll().get(1).getBList());
    System.out.println();

    System.out.println(bRepo.findAll());
    System.out.println(bRepo.findAll().get(0).getAList());
    System.out.println(bRepo.findAll().get(1).getAList());
  }
}
