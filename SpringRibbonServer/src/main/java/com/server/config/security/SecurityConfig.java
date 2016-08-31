package com.server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import com.server.config.filter.AuthenticationFilter;
import com.server.config.filter.CsrfHeaderFilter;
import com.server.config.filter.LoginFilter;
import com.server.config.security.auth.AuthenticationEntryPointImpl;
import com.server.config.security.service.TokenAuthenticationService;
import com.server.config.security.service.UserDetailsService;
import com.server.config.security.voter.UserRoleVoter;
import com.server.service.ServicePackage;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = { ServicePackage.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
		super(true);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					.anonymous()
				.and()
					.authorizeRequests()
						//FIXME
						.antMatchers("/user/**").permitAll()
						.antMatchers("/admin/**").authenticated()
						.antMatchers("/**").permitAll()
				.and()
					.addFilterBefore(new LoginFilter("/login", tokenAuthenticationService(), userDetailsService(),
						authenticationManager()), UsernamePasswordAuthenticationFilter.class)
					.addFilterBefore(new AuthenticationFilter(tokenAuthenticationService()),
						UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl())
				.and()
					.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
		//@formatter:on

		// http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// .anonymous().and().authorizeRequests().antMatchers("/public/**").permitAll().anyRequest()
		// .authenticated().and()
		// .addFilterBefore(new LoginFilter("/public/login", tokenAuthenticationService(), userDetailsService(),
		// authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		// .addFilterBefore(new AuthenticationFilter(tokenAuthenticationService()),
		// UsernamePasswordAuthenticationFilter.class)
		// .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl()).and()
		// .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService();
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return new TokenAuthenticationService();
	}

	@Bean
	public RoleVoter roleVoter() {
		return new UserRoleVoter();
	}

}
