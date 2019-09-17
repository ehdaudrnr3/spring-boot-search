package com.search.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.search.service.domain.User;
import com.search.service.repository.UserRepository;

@SpringBootApplication
public class SpringBootSearchApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSearchApplication.class, args);
	}
	
	@Bean
	InitializingBean saveUser(UserRepository repository, PasswordEncoder passwordEncoder) { 
		return () -> {
			repository.save(new User("ryan", passwordEncoder.encode("1234")));
			repository.save(new User("muzi", passwordEncoder.encode("1111")));
		};
	}
}
