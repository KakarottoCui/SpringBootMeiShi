package com.example.demo.main.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.main.entity.SysUser;
import com.example.demo.main.repository.SysUserRepository;

//创建会话
@Component
public class UserUtils {
	@Autowired
	private SysUserRepository userDAO;
	
	public SysUser getUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		SysUser user = userDAO.findByUsername(username);
		return user;
	}
	
	/**
	 * 判断是否有roleCode权限
	 * @param roleCode
	 * @return
	 */
	public Boolean hasRole(String roleCode) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> roleCodes=new ArrayList<>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roleCodes.add(authority.getAuthority());
		}
		return roleCodes.contains(roleCode);
	}
	
	public String getName() {
		if(hasRole("ROLE_ADMIN")) {
			return "开发者";
		}
		return getUser().getNickname();
	}
}
