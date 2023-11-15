package com.example.demo.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;

import com.example.demo.custom.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/***
 * 角色及权限
 
 *parent 为null时为角色，不为null时为权限
 */
@Entity
public class SysRole extends BaseEntity<Integer>{
	private String name;
	private String code;
	@JsonIgnore
	private SysRole parent;
	private Integer idx;
	@JsonIgnore
	private List<SysRole> children = new ArrayList<>();
	
	@Column(length=20)
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
	public SysRole getParent() {
		return parent;
	}
	public void setParent(SysRole parent) {
		this.parent = parent;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	public List<SysRole> getChildren() {
		return children;
	}
	
	public void setChildren(List<SysRole> children) {
		this.children = children;
	}
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	@Transient
	public Integer getParentId() {
		return parent==null?0:parent.getId();
	}
}
