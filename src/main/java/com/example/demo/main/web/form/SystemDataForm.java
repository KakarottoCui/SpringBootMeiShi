package com.example.demo.main.web.form;

import com.example.demo.custom.BaseForm;
import com.example.demo.main.entity.SystemDictionaries;

public class SystemDataForm extends BaseForm<Integer> {
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
	public SystemDictionaries getDictionaries() {
		return dictionaries;
	}
	public void setDictionaries(SystemDictionaries dictionaries) {
		this.dictionaries = dictionaries;
	}
}
