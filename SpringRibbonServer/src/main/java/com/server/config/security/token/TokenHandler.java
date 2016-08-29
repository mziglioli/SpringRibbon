package com.server.config.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.model.User;
import com.server.config.security.service.UserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenHandler {

	private final String secret = "juca";

	@Autowired
	private UserDetailsService userDetailsService;

	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return userDetailsService.loadUserByUsername(username);
	}

	public String createTokenForUser(User user) {
		return Jwts.builder().setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
