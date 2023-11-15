package com.example.demo.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.SysRole;
import com.example.demo.main.entity.SysUser;
import com.example.demo.main.repository.SysUserRepository;
import com.example.demo.main.security.User2;

@Service
public class SysUserService extends CommonService<SysUser, Integer> implements UserDetailsService {
	@Autowired
	private SysUserRepository userRepository;
	@Autowired
	private SysRoleService roleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user= userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> authorities=new ArrayList<>();
		List<SysRole> roles = user.getRoles();
		List<SysRole> haveRoles=new ArrayList<>();
		for (SysRole role : roles) {
			haveRoles.add(role);
			List<SysRole> children = roleService.findByParent(role);
			children.removeAll(haveRoles);
			haveRoles.addAll(children);
		}
		for(SysRole role : haveRoles) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		return new User2(user.getUsername(), user.getPassword(),user.getNickname(), authorities);
	}
	
	public SysUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public SysUser findByOpenid(String openId) {
		return userRepository.findByOpenid(openId);
	}
}
