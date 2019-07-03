package com.search.service.controller;

import java.awt.RenderingHints.Key;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.service.domain.KeywordHistory;
import com.search.service.history.HistoryService;
import com.search.service.keyword.KeywordService;
import com.search.service.models.RankModel;
import com.search.service.rank.RankService;

@RestController
@RequestMapping(value="/api/service")
public class ApiServiceController {
	
	@Autowired
	KeywordService keywordService;
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	RankService rankingServcie;
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(value="/place", method = RequestMethod.GET)
	public String keyword(
		@RequestParam(value = "keyword") String keyword,
		@RequestParam(value = "page") String page) throws Exception {
		
		String body = keywordService.findByKeyword(keyword, page);
		try {
			if(body != null) {
				historyService.save(keyword);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return body;
	}
	
	@RequestMapping(value="/history", method = RequestMethod.GET)
	public List<KeywordHistory> history() {
		return historyService.getHistory();
	}
	
	@RequestMapping(value="/rank", method = RequestMethod.GET)
	public List<RankModel> ranking() {
		return rankingServcie.getRankList();
	}
}
