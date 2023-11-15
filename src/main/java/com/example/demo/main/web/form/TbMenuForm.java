package com.example.demo.main.web.form;

import com.example.demo.custom.BaseForm;
import com.example.demo.main.entity.Permission;
import com.example.demo.main.entity.TbMenu;

public class TbMenuForm extends BaseForm<Integer> {
	private String name;	//菜单名称
	private String url;		//路径
	private Integer idx;	//排序
	private TbMenu parent;	//父级菜单
	private Integer parentId;	//父级菜单id
	private Permission role;	//权限
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
	public TbMenu getParent() {
		return parent;
	}
	public void setParent(TbMenu parent) {
		this.parent = parent;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Permission getRole() {
		return role;
	}
	public void setRole(Permission role) {
		this.role = role;
	}
	
}
