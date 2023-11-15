package com.example.demo.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.SystemData;
import com.example.demo.main.repository.SystemDataRepository;

@Service
public class SystemDataService  extends CommonService<SystemData, Integer>{

	@Autowired
	private SystemDataRepository dataDao;
	
	public List<SystemData> findByDictionariesCode(String string) {
		return dataDao.findByDictionariesCode(string);
	}

}
