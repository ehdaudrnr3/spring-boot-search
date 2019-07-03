package com.search.service.keyword.cache;

public interface CacheMemory {
	Boolean hasKey(String key);
	void insert(String key, String data);
	String selectByKey(String key);
}
