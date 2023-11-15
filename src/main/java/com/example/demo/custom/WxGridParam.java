package com.example.demo.custom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class WxGridParam {
	private Integer limit;
	private Integer page;
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Pageable getPageable() {
		PageRequest pageable=PageRequest.of(page-1, 10);
		return pageable;
	}
	
	public Pageable getPageable(Sort sort) {
		PageRequest pageable=PageRequest.of(page-1, 10, sort);
		return pageable;
	}
}
