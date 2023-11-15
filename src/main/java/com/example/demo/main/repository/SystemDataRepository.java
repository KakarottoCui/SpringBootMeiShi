package com.example.demo.main.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.SystemData;

@Repository
public interface SystemDataRepository extends CommonRepository<SystemData, Integer>{

	public List<SystemData> findByDictionariesCode(String string);

}
