package com.example.demo.main.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.custom.CommonRepository;
import com.example.demo.main.entity.Classify;

@Repository
public interface ClassifyRepository extends CommonRepository<Classify, Integer>{

}
