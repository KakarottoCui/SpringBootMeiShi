package com.example.demo.main.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;

import com.example.demo.custom.BaseEntity;

/**
 * 数据字典——数据
 
 *
 */
@Entity
public class SystemData extends BaseEntity<Integer> {
	private String name;
	private String code;
	private SystemDictionaries dictionaries;
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
	@ManyToOne
	@CreatedBy
	public SystemDictionaries getDictionaries() {
		return dictionaries;
	}
	public void setDictionaries(SystemDictionaries dictionaries) {
		this.dictionaries = dictionaries;
	}
	public SystemData(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate, String name, String code, SystemDictionaries dictionaries) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		this.name = name;
		this.code = code;
		this.dictionaries = dictionaries;
	}
	public SystemData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SystemData(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		// TODO Auto-generated constructor stub
	}
	
}
