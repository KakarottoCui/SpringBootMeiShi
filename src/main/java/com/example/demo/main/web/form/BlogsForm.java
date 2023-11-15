package com.example.demo.main.web.form;

import com.example.demo.custom.BaseForm;
import com.example.demo.main.entity.SysUser;

public class BlogsForm extends BaseForm<Integer> {
	private String title;	//标题
	private String text;	//文本内容
	private String TextType;	//文章类型
	private String Classify;	//美食分类
	private String blogStatic;	//美食状态
	private SysUser	user;		//博主id
	private boolean open;		//是否公开
	private String suggest;		//建议
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTextType() {
		return TextType;
	}
	public void setTextType(String textType) {
		TextType = textType;
	}
	public String getClassify() {
		return Classify;
	}
	public void setClassify(String classify) {
		Classify = classify;
	}
	public String getBlogStatic() {
		return blogStatic;
	}
	public void setBlogStatic(String blogStatic) {
		this.blogStatic = blogStatic;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	
}
