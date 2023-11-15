package com.example.demo.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.Blogs;
import com.example.demo.main.repository.BlogsRepository;

@Service
public class BlogsSerive extends CommonService<Blogs, Integer>{
	@Autowired
	
}
