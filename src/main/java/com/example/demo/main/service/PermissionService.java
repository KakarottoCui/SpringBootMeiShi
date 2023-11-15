package com.example.demo.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.Permission;
import com.example.demo.main.repository.PermissionRepository;

@Service
public class PermissionService extends CommonService<Permission, Integer> {

	@Autowired
	private PermissionRepository permissionDAO;
	
	public Permission findByCode(String pCode) {
		return permissionDAO.findByCode(pCode);
	}
	
}
