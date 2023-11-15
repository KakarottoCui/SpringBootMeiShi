package com.example.demo.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.example.demo.custom.BaseEntity;

/****
 * 权限表
 
 *
 */
@Entity
public class Permission extends BaseEntity<Integer>{
	private String name;	//权限名称
	private String code;	//权限代码
	private Integer idx;
	
	@Column(unique=true,nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(unique=true,nullable=false)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public Permission() {
		super();
	}
	public Permission(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate, String name, String code, Integer idx) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		this.name = name;
		this.code = code;
		this.idx = idx;
	}
	
}
