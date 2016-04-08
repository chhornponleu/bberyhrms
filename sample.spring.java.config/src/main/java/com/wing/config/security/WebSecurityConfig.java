package com.wing.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.wing.config.data.DataSourceConfig;
import com.wing.config.security.custom.CustomAuthenticationProvider;
import com.wing.config.security.custom.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Import({ WebSecurityInitializer.class, DataSourceConfig.class})
@PropertySource("classpath:properties/security.properties")
@ComponentScan(basePackages = "com.channey.app.services")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private String REMEMBER_ME_KEY;
	private String LOGIN_PAGE;
	private String LOGIN_SUCCESS_URL;
	private String LOGIN_PROCESSING_URL;
	private String RESOURCE_URL;
	private String LOGOUT_URL;

	@Autowired
	public void setAttributes(Environment env) {
		this.REMEMBER_ME_KEY = env.getProperty("security.remember_key");
		this.LOGIN_PAGE = env.getProperty("security.login_page_url");
		this.LOGIN_SUCCESS_URL = env.getProperty("security.login_success_url");
		this.LOGIN_PROCESSING_URL = env.getProperty("security.login_process_url");
		this.RESOURCE_URL = env.getProperty("security.resource_url");
		this.LOGOUT_URL = env.getProperty("security.logout_url");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/resources/**", "/user/ajaxLogin").permitAll()
			.antMatchers("/**").hasRole("ADMIN").and()
			//.anyRequest().permitAll().and()

			.formLogin().loginPage(LOGIN_PAGE).permitAll().and()

			.formLogin().defaultSuccessUrl(LOGIN_SUCCESS_URL).permitAll().and()

			.formLogin().loginProcessingUrl(LOGIN_PROCESSING_URL).usernameParameter("username").passwordParameter("password").and()

			.formLogin().failureUrl(LOGIN_PAGE).permitAll().and()

			.logout().logoutUrl(LOGOUT_URL).logoutSuccessUrl(LOGIN_PAGE) .and()
			
			.rememberMe().key(REMEMBER_ME_KEY).and()
			
			.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(RESOURCE_URL).antMatchers("/index");
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}

	@Bean
	CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
}
