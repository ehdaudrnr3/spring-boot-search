package com.search.service.rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.service.domain.KeywordHistory;
import com.search.service.history.HistoryService;
import com.search.service.models.RankModel;

@Service
public class RankService {
	
	@Autowired
	HistoryService historyService;
	
	public List<RankModel> getRankList() {
		List<RankModel> rankingList = new ArrayList<RankModel>();
		
		List<KeywordHistory> historyList = historyService.getHistory();
		Map<String, List<KeywordHistory>> keywordMap = 
			historyList.stream().collect(Collectors.groupingBy(KeywordHistory::getKeyword));
		
		for(String keyword : keywordMap.keySet()) {
			RankModel rank = new RankModel(keyword, keywordMap.get(keyword).size());
			rankingList.add(rank);
		}
		
		Collections.sort(rankingList);
		return rankingList;
	}
}
