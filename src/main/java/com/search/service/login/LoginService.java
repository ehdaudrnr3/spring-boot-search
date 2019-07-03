package com.search.service.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.search.service.domain.Account;
import com.search.service.repository.AccountRepository;

@Service
public class LoginService {
	
	@Autowired 
	AccountRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Account login(String userId, String password) throws Exception {
		Account account = repository.findByUserId(userId);
		if(account != null) {
			if(passwordEncoder.matches(password, account.getPassword())) {
				return account;
			}
		}
		
		return null;
	}
}
