package com.wing.config.security.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wing.app.entities.user.User;

public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 8117964152238334646L;

	public CustomUserDetails(String username, String password) {
		super.setUsername(username);
		super.setPassword(password);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();	
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
