package com.search.service.keyword.cache;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class KeywordCacheMemory implements CacheMemory{

	private HashMap<String, String> cacheMap = new HashMap<String, String>();
	
	@Override
	public Boolean hasKey(String key) {
		if(cacheMap.containsKey(key)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String selectByKey(String key) {
		if(hasKey(key)) {
			return cacheMap.get(key);
		}
		return null; 
	}
	
	@Override
	public void insert(String key, String jsonData) {
		cacheMap.put(key, jsonData);
	}
}
