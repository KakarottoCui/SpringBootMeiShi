package com.example.demo.main.web.form;

import com.example.demo.custom.BaseForm;
import com.example.demo.main.entity.Blogs;
import com.example.demo.main.entity.SysUser;

public class CommentForm extends BaseForm<Integer> {
	private String text;	//评论内容
	private SysUser user;	//评论人
	private Blogs blog;		//对应美食
	private boolean read2;	//是否阅读
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public Blogs getBlog() {
		return blog;
	}
	public void setBlog(Blogs blog) {
		this.blog = blog;
	}
	public boolean isRead2() {
		return read2;
	}
	public void setRead2(boolean read2) {
		this.read2 = read2;
	}
	
}
