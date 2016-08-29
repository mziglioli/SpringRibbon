package com.server.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.core.model.User;
import com.core.model.UserAuthority;
import com.core.tipo.Authorities;
import com.core.tipo.Status;
import com.server.service.UserService;

@RestController
public class HelloController extends RestControllerDefault<User, UserService> {

	@RequestMapping(value = "/user/all")
	public Collection<User> getAll() {
		return service.findAll();
	}

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
		service.save(user);
	}

	@RequestMapping(value = "/greeting")
	public String greet() {
		List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
		Random rand = new Random();

		int randomNum = rand.nextInt(greetings.size());
		return greetings.get(randomNum);
	}

	@RequestMapping(value = "/")
	public String home() {
		return "Hi!";
	}

}
