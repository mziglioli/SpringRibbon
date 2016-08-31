package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String login);

}
