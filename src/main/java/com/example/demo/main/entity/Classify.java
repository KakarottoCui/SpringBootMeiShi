package com.example.demo.main.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;

import com.example.demo.custom.BaseEntity;

/**
 * 个人分类
 
 *
 */
@Entity
public class Classify extends BaseEntity<Integer> {
	private String name;	//分类名称
	private SysUser user;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
	@CreatedBy
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
}
