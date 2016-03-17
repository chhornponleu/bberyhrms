package com.mcnc.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mcnc.app.user.UserService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserService userService;
	private final TokenAuthenticationService tokenAuthenticationService;
	
	public SpringSecurityConfig() {
		super(true);
		this.userService = new UserService();
		this.tokenAuthenticationService = new TokenAuthenticationService("123@abc", userService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.exceptionHandling().and()
			.anonymous().and()
			.servletApi().and()
			.authorizeRequests()

//			.antMatchers("/favicon.ico").permitAll()
//			.antMatchers("/resources/**").permitAll()
//			.antMatchers("/auth/**").permitAll()
			
			.antMatchers("/protected").authenticated()
			.antMatchers("/rest/**").authenticated()
			.anyRequest().permitAll().and()
			.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
			
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/protected"); //@TODO remove filter from /protected
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	@Override
	public UserService userDetailsService() {
		return userService;
	}
	
	@Bean
	public TokenAuthenticationService tokAuthenticationService() {
		return tokenAuthenticationService;
	}
}
