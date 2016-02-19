package com.mcnc.app;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcnc.app.user.UserService;
import com.mcnc.config.security.TokenAuthenticationService;
import com.mcnc.config.security.TokenHandler;

@Controller
@RequestMapping(value = "auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Account account) {
		
//		UsernamePasswordAuthenticationToken upt  = new UsernamePasswordAuthenticationToken(account
//				.getUsername(), account.getPassword());
//		
//		Authentication authentication = this.authenticationManager.authenticate(upt);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = this.generateToken(account);
		response.setHeader(TokenAuthenticationService.AUTH_HEADER_NAME, token);
		return token;
	}

	private String generateToken(Account account) {
		return new TokenHandler("123@abc", new UserService()).createTokenForAccount(account);				
	}

	public Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + 604800 * 1000);
	}
}
