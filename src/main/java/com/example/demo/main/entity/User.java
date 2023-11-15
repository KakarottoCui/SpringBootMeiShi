package com.example.demo.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户表
 
 *
 */
@Entity
public class User extends SysUser {
	private String name;	//昵称
	private String site;		//地区
	private String sex;			//性别
	private String position;	//职位
	private String introduction;	//简介
	@JsonIgnore
	private User user;
	@JsonIgnore
	private List<User> user2 = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@ManyToOne
	@CreatedBy
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	public List<User> getUser2() {
		return user2;
	}
	public void setUser2(List<User> user2) {
		this.user2 = user2;
	}
	
}
