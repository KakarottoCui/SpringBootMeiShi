package com.example.demo.main.web.controller;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.custom.CommonController;
import com.example.demo.main.entity.Permission;
import com.example.demo.main.web.form.PermissionForm;

@Controller
@RequestMapping(value="/system/permission")
public class PermissionController extends CommonController<Permission, Integer, PermissionForm> {

	@Override
	public Specification<Permission> buildSpec(PermissionForm form) {
		return null;
	}
	
}
