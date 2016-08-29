package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String login);

}
