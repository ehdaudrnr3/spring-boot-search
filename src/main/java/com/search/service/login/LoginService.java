package com.search.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.search.service.domain.User;
import com.search.service.repository.AccountRepository;

@Service
public class LoginService {
	
	@Autowired 
	AccountRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User login(String userId, String password) {
		User account = repository.findByUserId(userId);
		if(account != null) {
			if(passwordEncoder.matches(password, account.getPassword())) {
				return account;
			}
		}
		
		return null;
	}
}
