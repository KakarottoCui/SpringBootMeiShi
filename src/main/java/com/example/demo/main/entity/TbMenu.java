package com.example.demo.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;

import com.example.demo.custom.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 菜单表
 
 *
 */
@Entity
public class TbMenu extends BaseEntity<Integer> {
	private String name;	//菜单名称
	private String url;		//路径
	private Integer idx;	//排序
	@JsonIgnore
	private TbMenu parent;	//父级菜单
	private Permission role;	//权限
	//下级菜单
	@JsonIgnore
	private List<TbMenu> children = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	@ManyToOne
	@CreatedBy
	public TbMenu getParent() {
		return parent;
	}
	public void setParent(TbMenu parent) {
		this.parent = parent;
	}
	@ManyToOne
	@CreatedBy
	public Permission getRole() {
		return role;
	}
	public void setRole(Permission role) {
		this.role = role;
	}
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	@OrderBy(value="idx")
	public List<TbMenu> getChildren() {
		return children;
	}
	public void setChildren(List<TbMenu> children) {
		this.children = children;
	}
	public TbMenu(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate, String name, String url, Integer idx, TbMenu parent, Permission role,
			List<TbMenu> children) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		this.name = name;
		this.url = url;
		this.idx = idx;
		this.parent = parent;
		this.role = role;
		this.children = children;
	}
	public TbMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TbMenu(Integer id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate) {
		super(id, isUsed, createName, createDate, updateName, updateDate);
		// TODO Auto-generated constructor stub
	}
	public TbMenu(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	//父级菜单id
	@Transient
	public Integer getParentId() {
		return parent==null? 0 :parent.getId();
	}
	
}
