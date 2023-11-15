package com.example.demo.main.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.custom.AjaxResult;
import com.example.demo.custom.TreeTableModel;
import com.example.demo.main.entity.Permission;
import com.example.demo.main.entity.SysRole;
import com.example.demo.main.service.PermissionService;
import com.example.demo.main.service.SysRoleService;
import com.example.demo.main.web.form.SysRoleForm;

@Controller
@RequestMapping(value="/system/role")
public class SysRoleController{
	
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private PermissionService pService;
	
	@RequestMapping(value="/manage")
	public void manage() {
		
	}
	
	@RequestMapping(value="/edit")
	public void edit(SysRoleForm form, ModelMap map) {
		SysRole model = new SysRole();
		Integer parentId = form.getParentId();
		Integer id = form.getId();
		if(id != null) {
			model = roleService.findById(id);
			parentId = model.getParentId();
		}
		if(parentId != null) {	//父级id
			SysRole parent = roleService.findById(parentId);
			model.setParent(parent);
		}
		map.put("model", model);
		List<Permission> permissions = pService.findAll(Sort.by("idx"));
		map.put("roles", permissions);
	}
	
	//保存
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(SysRoleForm form) {
		try {
			SysRole model = new SysRole();
			Integer id = form.getId();
			if(id != null) {
				model = roleService.findById(id);
			}
			Integer parentId = form.getParentId();
			if(parentId != null) {
				model.setParent(roleService.findById(parentId));
			}else {
				model.setParent(null);
			}
			String pCode = form.getPermissionCode();
			if(StringUtils.hasText(pCode)) {
				Permission permission = pService.findByCode(pCode);
				model.setName(permission.getName());
				model.setCode(permission.getCode());
			}else {
				model.setName(form.getName());
				model.setCode(form.getCode());
			}
			model.setIdx(form.getIdx());
			roleService.save(model);
			return new AjaxResult("数据保存成功");
		} catch (Exception e) {
			return new AjaxResult(false, "数据保存失败");
		}
	}
	
	@RequestMapping(value="/treedata")
	@ResponseBody
	public Object treeList() {
		Sort sort = Sort.by("idx");
		List<SysRole> list = roleService.findAll(sort);
		return new TreeTableModel(list); 
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(SysRoleForm form) {
		try {
			roleService.deleteById(form.getId());
			return new AjaxResult("数据删除成功");
		} catch (Exception e) {
			return new AjaxResult(false,"数据删除失败");
		}
	}
}
