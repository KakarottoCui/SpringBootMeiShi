package com.example.demo.main.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.Comment;

@Repository
public interface CommentRepository extends CommonRepository<Comment, Integer> {

	public List<Comment> findByBlogId(Integer id);

}
