package com.klu.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.klu.model.User;
import com.klu.repo.UserRepo;
@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepo userRepo;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User exists=userRepo.findByUsername(username)
    .orElseThrow(()->new UsernameNotFoundException("username not found"));
    return new org.springframework.security.core.userdetails.User(
        exists.getUsername(),
        exists.getPassword(),
        Collections.singleton(new SimpleGrantedAuthority("ROLE_"+exists.getRole()))
        );
  }

}