package com.search.service.keyword;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.search.service.history.HistoryService;
import com.search.service.keyword.cache.CacheMemory;

@Service
public class KeywordService {
	
	final String SERVICE_URL = "https://dapi.kakao.com/v2/local/search/keyword";
	final String API_KEY = "KakaoAK d8f00aee6d5f308c80f6b82fb2d79825";
	
	@Autowired
	CacheMemory cacheMemory;
	
	public String findByKeyword(String keyword, String page) {
		
		String key = makeKey(keyword, page);
		if(cacheMemory.hasKey(key)) {
			return cacheMemory.selectByKey(key);
		} 
		
		RestTemplate templete = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", API_KEY);
		
		URI uri = UriComponentsBuilder.fromUriString(SERVICE_URL)
				.queryParam("query", keyword)
				.queryParam("page", page)
				.build()
				.encode(StandardCharsets.UTF_8)
				.toUri();
		
		ResponseEntity<String> resposne = templete.exchange(uri, HttpMethod.GET, new HttpEntity(headers), String.class);
		cacheMemory.insert(key, resposne.getBody());
		
		return resposne.getBody();
	}
	
	private String makeKey(String keyword, String page) {
		return keyword+"-"+page;
	}
}
