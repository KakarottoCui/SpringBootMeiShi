package com.example.demo.main.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.Blogs;

@Repository
public interface BlogsRepository extends CommonRepository<Blogs, Integer>{

	public Blogs findByUserNicknameAndUpdateDate(String name, String updateDate);

	public Page<Blogs> findByBlogStatic(String s, Pageable pagea);

}
