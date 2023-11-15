package com.example.demo.main.web.form;

import com.example.demo.custom.BaseForm;
import com.example.demo.main.entity.SysUser;

public class ClassifyForm extends BaseForm<Integer> {
	private String name;	//分类名称
	private SysUser user;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
}
