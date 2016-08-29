package com.server.config.security.voter;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.core.util.Catalago;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserRoleVoter extends RoleVoter {

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

		if (authentication == null) {
			return ACCESS_DENIED;
		}
		int result = ACCESS_ABSTAIN;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (ConfigAttribute attribute : attributes) {
			if (this.supports(attribute)) {
				result = ACCESS_DENIED;
				for (GrantedAuthority authority : authorities) {
					if (authority.getAuthority().equals(Catalago.ROLE_ADMIN)
							|| attribute.getAttribute().equals(authority.getAuthority())) {
						return ACCESS_GRANTED;
					}
				}
			}
		}
		return result;
	}
}