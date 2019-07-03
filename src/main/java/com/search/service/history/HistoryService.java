package com.search.service.history;

import java.awt.RenderingHints.Key;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.service.domain.KeywordHistory;
import com.search.service.repository.HistoryRepository;

@Service
public class HistoryService {

	@Autowired
	HistoryRepository repository;
	
	public KeywordHistory save(String keyword) {
		KeywordHistory history = new KeywordHistory(keyword); 
		repository.save(history);
		return history;
	}
	
	public List<KeywordHistory> getHistory() {
		List<KeywordHistory> history = repository.findAll();
		Collections.sort(history);
		return history;
	}
}
