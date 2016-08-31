package com.server.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class RestTemplateDefault {

	private static HttpHeaders requestHeaders;

	private static List<String> cookies;

	public static HttpHeaders getHeaders() {
		if (requestHeaders == null) {
			requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json");
			requestHeaders.add("Content-Type", "text/plain");
		}
		return requestHeaders;
	}

	public static List<String> getCookies() {
		return cookies;
	}

	public static List<HttpMessageConverter<?>> getConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());

		return converters;
	}
}
