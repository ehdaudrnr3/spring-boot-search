package com.search.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.search.service.domain.Account;

@Repository
public class AccountRepository {
	
	@Autowired
	UserRepository repository;
	
	public Account findByUserId(String userId) {
		for(Account user : repository.findAll()) {
			if(user.getUserId() != null && user.getUserId().equals(userId)) {
				return user;
			}
		}
		return null;
	}
}
