package com.simple.rest_like_api.JPAAssociation.bidirectional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
class ManyTOOne_bidirectionalTest {
  @Autowired private AManyToOneRepo_bi aRepo;

  @Autowired private BManyToOneRepo_Bi bRepo;

  @Test
  public void able_to() {
    BManyToOne_Bi b = new BManyToOne_Bi();
    b.setData("bbb");

    // ref
    // https://stackoverflow.com/questions/7428089/unsupportedoperationexception-merge-saving-many-to-many-relation-with-hibernate
    // JPA entity 의 멤버는 mutable 한 자료구조여야함
    // Arrays.asList()는 mutable 한 리스트를 반환하지 않아 아래 코드는
    // UnsupportedOperationException 발생함
    // b.setAList(Arrays.asList());
    b.setAList(new ArrayList<>());
    bRepo.save(b);

    AManyTOOne_Bi a1 = new AManyTOOne_Bi();
    a1.setData("aaa");
    a1.setB(b);
    aRepo.save(a1);

    AManyTOOne_Bi a2 = new AManyTOOne_Bi();
    a2.setData("aaa");
    a2.setB(b);
    aRepo.save(a2);

    b.getAList().add(a1);
    b.getAList().add(a2);
    bRepo.save(b);

    System.out.println(aRepo.findAll());
    System.out.println(aRepo.findAll().get(0).getB());

    System.out.println(bRepo.findAll());
    System.out.println(bRepo.findAll().get(0).getAList().get(0));
    System.out.println(bRepo.findAll().get(0).getAList().get(1));
  }
}
