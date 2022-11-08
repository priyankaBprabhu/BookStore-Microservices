package com.bl.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


	Optional<User> findByEmail(String email);

	
}
