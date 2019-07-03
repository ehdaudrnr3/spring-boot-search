package com.search.service.models;

import java.math.BigDecimal;

public class RankModel implements Comparable<RankModel>{
	private String keyword;
	private int count;
	
	public RankModel() {
	}
	
	public RankModel(String keyword, int count) {
		this.keyword = keyword;
		this.count = count;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(RankModel o) {
		if(this.getCount() < o.getCount()) {
			return 1;
		} else if(this.getCount() > o.getCount()) {
			return -1;
		}
		return 0;
	}
}
