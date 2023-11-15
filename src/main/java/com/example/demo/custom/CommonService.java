package com.example.demo.custom;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

public class CommonService<T,ID> {
	@Autowired
	private CommonRepository<T, ID> baseDAO;
	
	public List<T> findAll(){
		return baseDAO.findAll();
	}
	
	public T findById(ID id) {
		Optional<T> optional = baseDAO.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@Transactional
	public void save(T entity) {
		baseDAO.save(entity);
	}
	
	public void delete(T entity) {
		baseDAO.delete(entity);
	}
	
	@Transactional
	public void deleteById(ID id) {
		baseDAO.deleteById(id);
	}
	
	public List<T> findAll(Sort sort){
		return baseDAO.findAll(sort);
	}
	
	public List<T> findAll(Specification<T> spec){
		return baseDAO.findAll(spec);
	}
	
	public Page<T> findAll(Pageable pageable){
		return baseDAO.findAll(pageable);
	}
	
	public Page<T> findAll(Specification<T> spec,Pageable pageable){
		return baseDAO.findAll(spec,pageable);
	}
	
	public List<T> findAll(Specification<T> spec,Sort sort){
		return baseDAO.findAll(spec,sort);
	}
}
