package com.application.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.application.api.security.util.ExceptionHandlerFilter;
import com.application.api.security.util.JwtFilter;

@EnableWebSecurity
public class SecurityConfiguration {
	
	private JwtFilter jwtFilter;
	private ExceptionHandlerFilter exceptionHandlerFilter;
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	public SecurityConfiguration(JwtFilter jwtFilter, ExceptionHandlerFilter exceptionHandlerFilter, AuthenticationConfiguration authenticationConfiguration) {
		super();
		this.jwtFilter = jwtFilter;
		this.exceptionHandlerFilter = exceptionHandlerFilter;
		this.authenticationConfiguration = authenticationConfiguration;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().sameOrigin();
		
		httpSecurity.authorizeHttpRequests()
		.antMatchers("/api/authenticate").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/api/employee/**", "/api/department/**").hasAnyAuthority("role.USER", "role.ADMIN")
		.antMatchers("/api/user/**", "/api/role/**", "/api/role-data/**").hasAuthority("role.ADMIN")
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.addFilterBefore(exceptionHandlerFilter, JwtFilter.class);
		
		return httpSecurity.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}
