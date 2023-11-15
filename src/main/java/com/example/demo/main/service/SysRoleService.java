package com.example.demo.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.SysRole;
import com.example.demo.main.repository.SysRoleRepository;

@Service
public class SysRoleService extends CommonService<SysRole, Integer>{
	@Autowired
	private SysRoleRepository sysRoleDAO;
	
	public List<SysRole> findByParentIsNull() {
		return sysRoleDAO.findByParentIsNull();
	}

	public SysRole findbyCode(String code) {
		return sysRoleDAO.findByCode(code);
	}

	public List<SysRole> findByParent(SysRole role) {
		return sysRoleDAO.findByParent(role);
	}

}
