package com.klu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public PasswordEncoder userDetails() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
	    http.csrf(csrf->csrf.disable())
	    .authorizeHttpRequests(
	        auth-> auth.requestMatchers("/auth/login","/auth/register").permitAll()
	        .requestMatchers("/admin").hasRole("ADMIN")
	        .anyRequest().authenticated()
	        )
	    .formLogin(form-> form.defaultSuccessUrl("/dashboard"))
	    .logout(logout->logout.logoutSuccessUrl("/"));
	    return http.build();
	  }
	



@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	return config.getAuthenticationManager();
	
}
}
