package com.server.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.server.model.User;
import com.server.model.UserAuthority;
import com.server.service.UserService;
import com.server.tipo.Authorities;
import com.server.tipo.Status;

@RestController
public class HelloController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/")
	public String home() {
		return "Hi! Welcome to ....";
	}

	@RequestMapping(value = "/greeting")
	public String greet() {
		List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
		Random rand = new Random();

		int randomNum = rand.nextInt(greetings.size());
		return greetings.get(randomNum);
	}

	// FIXME
	// remove, just to test
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public void auth() {
		User user = new User();
		user.setNome("test");
		user.setPassword("test");
		user.setStatus(Status.ATIVO.getDescricao());
		user.setUsername("test");
		Collection<UserAuthority> authorities = new HashSet<>();
		authorities.add(new UserAuthority(Authorities.USER.getRole()));
		user.setAuthorities(authorities);
		userService.save(user);
	}
}