package com.search.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigure extends WebMvcConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder passwrodEncoder() {
		return new BCryptPasswordEncoder();
	}

}
