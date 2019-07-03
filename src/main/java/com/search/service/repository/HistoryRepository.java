package com.search.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.search.service.domain.KeywordHistory;

public interface HistoryRepository extends JpaRepository<KeywordHistory, Long>{
	
}
