package com.wing.config.security.custom;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new CustomUserDetails(username, "123");
	}
}
