package com.example.demo.main.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.Permission;

@Repository
public interface PermissionRepository extends CommonRepository<Permission, Integer> {

	public Permission findByCode(String pCode);

}
