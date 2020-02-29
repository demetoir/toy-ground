package com.example.demetoir.domain;

import com.example.demetoir.persistance.MemberRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTest {

  @Autowired private MemberRepository memberRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Test
  public void insertDummyMember() {
    for (int i = 0; i < 100; i++) {
      Member member = new Member();

      member.setUid("user" + i);
      member.setUpw(passwordEncoder.encode("pw" + i));
      member.setUname("user" + i);

      MemberRole role = new MemberRole();
      if (i <= 80) {
        role.setRoleName("BASIC");
      } else if (i <= 90) {
        role.setRoleName("MANAGER");
      } else {
        role.setRoleName("ADMIN");
      }
      member.setRoles(Collections.singletonList(role));

      memberRepository.save(member);
    }
  }

  @Test
  public void findMemberById() {
    Optional<Member> result = memberRepository.findById("user43");

    result.ifPresent(member -> log.info("member " + member));
  }
}
