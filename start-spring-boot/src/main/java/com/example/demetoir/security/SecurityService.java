package com.example.demetoir.security;

import com.example.demetoir.persistance.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log
public class SecurityService implements UserDetailsService {

  @Autowired private MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    return memberRepository.findById(s).filter(m -> m != null).map(SecurityUser::new).get();
  }
}
