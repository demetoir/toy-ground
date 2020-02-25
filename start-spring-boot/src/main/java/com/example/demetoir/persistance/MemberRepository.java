package com.example.demetoir.persistance;

import com.example.demetoir.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {}
