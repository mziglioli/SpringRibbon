package com.server.config.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.core.model.User;
import com.server.repository.UserRepository;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private final AccountStatusUserDetailsChecker detailsChecker;

	public UserDetailsService() {
		this.detailsChecker = new AccountStatusUserDetailsChecker();
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		detailsChecker.check(user);
		return user;
	}
}
