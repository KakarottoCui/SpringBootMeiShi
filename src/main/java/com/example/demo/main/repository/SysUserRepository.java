package com.example.demo.main.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.SysUser;

@Repository
public interface SysUserRepository extends CommonRepository<SysUser, Integer> {

	public SysUser findByUsername(String username);

	public SysUser findByOpenid(String openId);

}
