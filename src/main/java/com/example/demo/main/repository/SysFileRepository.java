package com.example.demo.main.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.SysFile;

@Repository
public interface SysFileRepository extends CommonRepository<SysFile, Integer>{

	public SysFile findByUuid(String uuid);

}
