package com.example.demo.main.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class User2 extends User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User2(String username, String password, String name, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.name = name;
	}
	
}
