package com.example.demo.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedBy;

import com.example.demo.custom.BaseEntity;

/**
 * 评论表
 
 *
 */
@Entity
public class Comment extends BaseEntity<Integer> {
	private String text;	//评论内容
	private SysUser user;	//评论人
	private Blogs blog;		//对应美食
	private boolean read2;	//是否阅读
	@Column(length=2000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@ManyToOne
	@CreatedBy
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	@ManyToOne
	@CreatedBy
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
