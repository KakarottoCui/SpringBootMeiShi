package com.example.demo.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.TbMenu;
import com.example.demo.main.repository.MenuRepository;
import com.example.demo.main.security.UserUtils;

@Service
public class MenuService extends CommonService<TbMenu, Integer> {
	@Autowired
	private MenuRepository menuReopsitory;
	@Autowired
	private UserUtils userUtils;
	
	/**
	 * 取出用户有权限的菜单项
	 * @return
	 */
	public List<TbMenu> findAuditMenu() {
		List<TbMenu> menus;
		//当前用户为后门登录
		if(userUtils.hasRole("ROLE_ADMIN")) {
			menus = menuReopsitory.findByParentIsNull();
		}else {
			menus = auditMenu(menuReopsitory.findByParentIsNullOrderByIdx());
		}
		return menus;
	}

	//加载下级菜单
	private List<TbMenu> auditMenu(List<TbMenu> menus) {
		List<TbMenu> list = new ArrayList<>();
		for(TbMenu menu : menus) {
			String code = menu.getRole().getCode();
			if(userUtils.hasRole(code)) {
				list.add(menu);
				//下级菜单不为空,有下级菜单添加下级菜单
				if(menu.getChildren() != null && !menu.getChildren().isEmpty()) {
					menu.setChildren(auditMenu(menu.getChildren()));
				}
			}
		}
		return list;
	}

}
