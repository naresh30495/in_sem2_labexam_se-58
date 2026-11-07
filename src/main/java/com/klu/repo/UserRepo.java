package com.klu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	public Optional<User> findByUsername(String Username);

}
