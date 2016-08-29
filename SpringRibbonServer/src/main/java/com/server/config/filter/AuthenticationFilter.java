package com.server.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.server.config.security.service.TokenAuthenticationService;

public class AuthenticationFilter extends OncePerRequestFilter {

	private final TokenAuthenticationService tokenAuthenticationService;

	public AuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {
		Authentication authentication = tokenAuthenticationService.getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filter.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().startsWith("/public/");
	}

}
