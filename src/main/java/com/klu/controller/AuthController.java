

package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.config.JwtUtil;
import com.klu.model.User;
import com.klu.repo.UserRepo;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserRepo userRepo;
  
  @Autowired
  private PasswordEncoder encoder;
  
  @Autowired
  private AuthenticationManager authManager;
  
  @Autowired
  private JwtUtil jwtUtil;
  
  @PostMapping("/register")
  public String register(@RequestBody User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    userRepo.save(user);
    return "User Registered Successfully";
  }
  
  @PostMapping("/login")
  public String login(@RequestBody User user) {
    authManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            user.getUsername(), user.getPassword())
        );
    return jwtUtil.generateToken(user.getUsername());
  }
}