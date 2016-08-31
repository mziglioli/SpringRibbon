package com.loader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.loader.model.User;
import com.loader.ribbon.RibbonClientConfiguration;

@RestController
@RibbonClient(name = "spring-server", configuration = RibbonClientConfiguration.class)
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/hi")
	public String hi(@RequestParam(value = "name", defaultValue = "Artaban") String name) {
		String greeting = this.restTemplate.getForObject("http://spring-server/greeting", String.class);
		return String.format("%s, %s!", greeting, name);
	}

	@RequestMapping("/all")
	public List<User> all() {
		// HttpEntity<List> requestEntity = new HttpEntity<List>(restTemplateDefault.getHeaders());
		//
		// ResponseEntity<List> responseEntity = restTemplate.exchange(WebServicesURL.ALL_CATEGORIAS, HttpMethod.GET,
		// requestEntity, List.class);
		ResponseEntity<List<User>> response = restTemplate.exchange("http://spring-server/user/", HttpMethod.GET,
				HttpEntity.EMPTY, new ParameterizedTypeReference<List<User>>() {
				});
		return response.getBody();// this.restTemplate.getForObject("http://spring-server/user/all", String.class);
	}
}