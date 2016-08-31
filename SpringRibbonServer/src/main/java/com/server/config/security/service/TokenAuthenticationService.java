package com.server.config.security.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.server.config.security.auth.UserAuthentication;
import com.server.config.security.token.TokenHandler;
import com.server.model.User;

@Service
public class TokenAuthenticationService {

	private static final String AUTH_COOKIE_NAME = "X-AUTH-TOKEN";

	@Autowired
	private TokenHandler tokenHandler;

	public void addAuthentication(HttpServletResponse response, UserAuthentication userAuthentication) {
		Cookie cookie = new Cookie(AUTH_COOKIE_NAME, tokenHandler.createTokenForUser(userAuthentication.getDetails()));
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(cookie);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, AUTH_COOKIE_NAME);
		try {
			if (cookie != null) {
				String token = cookie.getValue();
				if (token != null) {
					final User user = tokenHandler.parseUserFromToken(token);
					if (user != null) {
						return new UserAuthentication(user);
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
