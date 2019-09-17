package com.search.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.search.service.login.AuthFailureHandler;
import com.search.service.login.AuthProvider;

@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthProvider authProvider;
	
	@Autowired
	AuthFailureHandler authFailureHandler;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").authenticated()
				.antMatchers("/api/**").authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("userId")
				.failureHandler(authFailureHandler)
				.failureUrl("/login?error=true")
				.and()
			.logout()
				.permitAll()
			.and().authenticationProvider(authProvider);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder  passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
 	}
}
