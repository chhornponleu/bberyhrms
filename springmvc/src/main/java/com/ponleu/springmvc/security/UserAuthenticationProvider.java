package com.ponleu.springmvc.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ponleu.springmvc.data.service.RoleService;
import com.ponleu.springmvc.data.service.UserService;
import com.ponleu.springmvc.model.RoleDTO;
import com.ponleu.springmvc.model.UserDTO;

public class UserAuthenticationProvider implements AuthenticationProvider {

	final Logger logger = LoggerFactory.getLogger(UserAuthenticationProvider.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws NullPointerException{

		String username = this.retrieveUserName(authentication);
		String password = this.retrievePassword(authentication);
		
		UserDTO dbUser = this.getDBUserByUsername(username);
		 
		if(dbUser == null) {
			throw new UsernameNotFoundException("User '" + username + "' was not found.");
		}
		// @TODO replace this condition with hashed  password
		else if (!dbUser.getPassword().equals(password)) { 
			throw new UsernameNotFoundException("Username and password do not match.");
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, this.getGrantedAuthority(dbUser.getUserId()));
	}
	
	private Collection<? extends GrantedAuthority> getGrantedAuthority(int userId) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<RoleDTO> roleList = roleService.getRoleListByUserId(userId);
		if(roleList != null && !roleList.isEmpty()) {
			for(RoleDTO role: roleList) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}
		return authorities;
	}
	
	private UserDTO getDBUserByUsername(String username) {
		return userService.getByUsername(username);
	}
	
	private String retrieveUserName(Authentication authentication) {
        if (isInstanceOfUserDetails(authentication)) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        else {
            return authentication.getPrincipal().toString();
        }
    }

    private String retrievePassword(Authentication authentication) {
        if (isInstanceOfUserDetails(authentication)) {
            return ((UserDetails) authentication.getPrincipal()).getPassword();
        }
        else {
            if (authentication.getCredentials() == null) {
                return null;
            }
            return authentication.getCredentials().toString();
        }
    }

    private boolean isInstanceOfUserDetails(Authentication authentication) {
        return authentication.getPrincipal() instanceof UserDetails;
    }

    @Override
	public boolean supports(Class<?> clazz) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz));
	}	
}
