package com.example.demo.main.web.form;

import com.example.demo.custom.BaseForm;

public class SystemDictionariesForm extends BaseForm<Integer> {
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
}
