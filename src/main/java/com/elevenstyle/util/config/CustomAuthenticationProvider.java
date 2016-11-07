package com.elevenstyle.util.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.elevenstyle.service.CustomUserService;

/**
 * 登陆自定义认证
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月7日 下午12:08:33
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private CustomUserService customUserService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserDetails user = customUserService.loadUserByUsername(email);
		if (passwordEncoder().matches(password, user.getPassword())) {
			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
			return new UsernamePasswordAuthenticationToken(user, password, authorities);
			}
		throw new BadCredentialsException("Wrong password.");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
	}
}
