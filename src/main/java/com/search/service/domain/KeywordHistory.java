package com.search.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KeywordHistory implements Comparable<KeywordHistory>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long uid;
	private String keyword;
	private Date createDate;
	
	public KeywordHistory() {}
	
	public KeywordHistory(String keyword) {
		this.keyword = keyword;
		this.createDate = new Date();
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public int compareTo(KeywordHistory o) {
		if(this.getCreateDate().compareTo(o.getCreateDate()) < 0) {
			return 1;
		} else if(this.getCreateDate().compareTo(o.getCreateDate()) > 0) {
			return -1;
		}
		return 0;
	}
}
