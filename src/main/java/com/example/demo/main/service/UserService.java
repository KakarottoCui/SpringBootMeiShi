package com.example.demo.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.User;
import com.example.demo.main.repository.UserRepository;

@Service
public class UserService extends CommonService<User, Integer>{
	
	@Autowired
	private UserRepository userDao;

	public User findByOpenid(String openId) {
		return userDao.findByOpenid(openId);
	}

}
