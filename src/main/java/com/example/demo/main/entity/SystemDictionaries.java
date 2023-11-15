package com.example.demo.main.entity;

import javax.persistence.Entity;

import com.example.demo.custom.BaseEntity;

/**
 * 数据字典——字典
 
 *
 */
@Entity
public class SystemDictionaries extends BaseEntity<Integer> {
	private String name;
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public SystemDictionaries(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate, String name, String code) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		this.name = name;
		this.code = code;
	}
	public SystemDictionaries() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SystemDictionaries(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		// TODO Auto-generated constructor stub
	}
	
}
