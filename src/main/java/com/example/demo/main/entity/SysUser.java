package com.example.demo.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.example.demo.custom.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户表
 
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class SysUser extends BaseEntity<Integer>{
	private String username;
	private String password;
	private String nickname;
	private String openid;
	
	@JsonIgnore
	private List<SysRole> roles=new ArrayList<>();
	@JsonIgnore
	private List<Blogs> blogs = new ArrayList<>();
	
	@Column(length=20,unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(length=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@OneToMany(mappedBy="user")
	public List<Blogs> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blogs> blogs) {
		this.blogs = blogs;
	}
	@ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinTable(name="sys_user_role",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public SysUser(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate, String username, String password, String nickname, List<SysRole> roles) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.roles = roles;
	}
	public SysUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SysUser(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		// TODO Auto-generated constructor stub
	}
	//获取权限代码
	@Transient
	public String getRoleCodes() {
		String str="";
		for (SysRole role : getRoles()) {
			str+=role.getCode()+",";
		}
		if(str.indexOf(",")>0) {
			str=str.substring(0,str.length()-1);
		}
		return str;
	}
	
	//获取权限名称	
	@Transient
	public String getRoleNames() {
		String str="";
		for (SysRole role : getRoles()) {
			str+=role.getName()+",";
		}
		if(str.length()>0) {
			str=str.substring(0, str.length()-1);
		}
		return str;
	}
	
	@Transient
	public Integer getBlogSize() {
		return getBlogs().size();
	}
	
}
