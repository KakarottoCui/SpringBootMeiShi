package com.example.demo.main.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.SysRole;

@Repository
public interface SysRoleRepository extends CommonRepository<SysRole, Integer>{

	public List<SysRole> findByParentIsNull();

	public SysRole findByCode(String code);

	public List<SysRole> findByParent(SysRole role);
	
	/*@Modifying
	@Query(value="update SysRole t set t.code=?2 where t.code=?1")
	public void changeCode(String oldCode, String newCode);
	
	@Modifying
	@Query(value="update SysRole t set t.name=?2 where t.name=?1")
	public void changeName(String oldName, String newName);*/

}
