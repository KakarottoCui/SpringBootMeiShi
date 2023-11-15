package com.example.demo.main.web.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.custom.AjaxResult;
import com.example.demo.custom.CommonController;
import com.example.demo.custom.TreeTableModel;
import com.example.demo.main.entity.Permission;
import com.example.demo.main.entity.TbMenu;
import com.example.demo.main.service.MenuService;
import com.example.demo.main.service.PermissionService;
import com.example.demo.main.web.form.TbMenuForm;

@Controller
@RequestMapping(value="/system/menu")
public class MenuController extends CommonController<TbMenu, Integer, TbMenuForm> {
	@Autowired
	private MenuService mService;
	@Autowired
	private PermissionService pService;
	
	@Override
	public void edit(TbMenuForm form, ModelMap map) throws InstantiationException, IllegalAccessException {
		TbMenu model = new TbMenu();
		Integer id = form.getId();
		Integer parentId = form.getParentId();	//父级id
		if(id != null) {
			model = mService.findById(id);
			parentId = model.getParentId();
		}
		if(parentId != null) {	//父级id
			TbMenu parent = mService.findById(parentId);
			model.setParent(parent);
		}
		map.put("model", model);
		List<Permission> permissions = pService.findAll(Sort.by("idx"));
		map.put("roles", permissions);
	}
	
	@Override
	public Object save(TbMenuForm form) {
		try {
			TbMenu model = new TbMenu();
			Integer id = form.getId();
			if(id != null) {
				model = mService.findById(id);
			}
			//父级菜单id
			Integer parentId = form.getParentId();
			if(parentId == null) {
				model.setParent(null);
			}else {
				model.setParent(new TbMenu(parentId));
			}
			//权限id
			Integer roleId = form.getRole().getId();
			if(roleId == null) {
				model.setRole(null);
			}else {
				Permission role = pService.findById(roleId);
				model.setRole(role);
			}
			BeanUtils.copyProperties(form, model,"id","role","parent");
			mService.save(model);
			return new AjaxResult("数据保存成功！");
		} catch (Exception e) {
			return new AjaxResult(false,"数据保存失败");
		}
	}

	@RequestMapping(value="/treedata")
	@ResponseBody
	public Object treedata() {
		Sort sort = Sort.by("idx");
		List<TbMenu> list = mService.findAll(sort);
		return new TreeTableModel(list);
	}
}
