package com.example.demo.main.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.User;

@Repository
public interface UserRepository extends CommonRepository<User, Integer>{

	public User findByOpenid(String openId);

}
