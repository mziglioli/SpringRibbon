package com.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.core.model.User;
import com.server.repository.UserRepository;

@Service
public class UserService extends ServiceDefault<User, UserRepository> {

	public User findByUsername(String username) {
		return getRepository().findByUsername(username);
	}

	public Long getQtdUsers() {
		return getRepository().count();
	}

	public User getUserFromUserLogado() {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = findByUsername(username);
		return user;
	}

	public List<User> getFriends(List<Long> ids) {
		List<User> users = new ArrayList<User>();
		for (Long id : ids) {
			User u = getRepository().findOne(id);
			if (u != null)
				users.add(u);
		}
		return users;
	}

}
