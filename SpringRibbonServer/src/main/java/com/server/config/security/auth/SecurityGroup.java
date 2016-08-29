package com.server.config.security.auth;

import java.util.Collection;

import com.core.model.UserAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityGroup {

	private String name;

	private String description;

	private Collection<UserAuthority> authorities;

}
