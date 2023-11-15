package com.example.demo.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.Comment;
import com.example.demo.main.repository.CommentRepository;

@Service
public class CommentService extends CommonService<Comment, Integer> {
	
	@Autowired
	private CommentRepository commentDao;

	public List<Comment> findByBlogId(Integer id) {
		return commentDao.findByBlogId(id);
	}

}
