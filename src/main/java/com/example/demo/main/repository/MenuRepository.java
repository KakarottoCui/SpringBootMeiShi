package com.example.demo.main.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.TbMenu;

@Repository
public interface MenuRepository extends CommonRepository<TbMenu, Integer> {

	public List<TbMenu> findByParentIsNull();

	public List<TbMenu> findByParentIsNullOrderByIdx();

}
