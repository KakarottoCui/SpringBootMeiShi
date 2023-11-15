package com.example.demo.main.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.SystemDictionaries;

@Repository
public interface SystemDictionariesRepository extends CommonRepository<SystemDictionaries, Integer>{

}
