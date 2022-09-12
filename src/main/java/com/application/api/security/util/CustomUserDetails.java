package com.application.api.security.util;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.application.api.security.entity.User;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> roles = user.getRoleData().stream()
				.map(roleData -> new SimpleGrantedAuthority(roleData.getRole().getRole()))
				.toList();
		return roles;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isUserNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isUserNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isUserCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.isUserEnabled();
	}
	
	public boolean isAdmin() {
		return getAuthorities().stream()
				.anyMatch(role -> role.getAuthority().equals("role.ADMIN"));
	}

}
