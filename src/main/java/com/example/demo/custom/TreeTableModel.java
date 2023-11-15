package com.example.demo.custom;

import java.util.List;

/**
 * treeTable返回类型
 
 *
 */
public class TreeTableModel {
	private Integer code=0;
	private String msg="ok";
	private Integer count;
	private List data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public TreeTableModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TreeTableModel(Integer code, String msg, Integer count, List data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	public TreeTableModel(List data) {
		super();
		this.count=data.size();
		this.data = data;
	}
	
	
	
}
