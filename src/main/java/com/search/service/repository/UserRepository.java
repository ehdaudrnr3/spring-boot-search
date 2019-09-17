package com.search.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.search.service.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
