package com.server.config.filter;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfHeaderFilter extends OncePerRequestFilter {

    public static final String XSRF_TOKEN = "XSRF-TOKEN";
    public static final String X_XSRF_TOKEN = "X-XSRF-TOKEN";
    private final Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    private final AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String csrfTokenValue = request.getHeader(X_XSRF_TOKEN);
        Cookie cookie = WebUtils.getCookie(request, XSRF_TOKEN);

        if (cookie == null || csrfTokenValue == null  || !csrfTokenValue.equals(cookie.getValue())) {
            accessDeniedHandler.handle(request, response, new AccessDeniedException("Missing or non-matching XSRF-TOKEN"));
            return;
        }

        addCsrfCookie(response);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return allowedMethods.matcher(request.getMethod()).matches() || request.getServletPath().startsWith("/public/");
    }

    public static void addCsrfCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(XSRF_TOKEN, UUID.randomUUID().toString());
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
    }

}
