package com.wing.config.security.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wing.app.entities.user.User;
import com.wing.app.services.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = userService.getByUsername(username);
		
		if(user != null) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
				Authentication auth = new UsernamePasswordAuthenticationToken(username,password, getGrantedAuths(user));
				return auth;
			} 
			else {
				throw new BadCredentialsException("Invalid login password.");
			}
		} 
		else {
			throw new BadCredentialsException("Invalid login username and password");
		}
	}
	
	public List<GrantedAuthority> getGrantedAuths(User user) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		String[] roles = user.getRoles().split(",");
		for(int i=0;i<roles.length; i++) {
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + roles[i]));
		}
		return grantedAuths;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
