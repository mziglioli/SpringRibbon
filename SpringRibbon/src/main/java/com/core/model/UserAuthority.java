package com.core.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "authority")
public class UserAuthority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = -6531931258094044470L;
	@NotNull
	private String authority;

}
