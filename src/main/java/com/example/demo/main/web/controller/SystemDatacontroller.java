package com.example.demo.main.web.controller;

import java.util.HashSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.custom.CommonController;
import com.example.demo.main.entity.SystemData;
import com.example.demo.main.service.SystemDataService;
import com.example.demo.main.web.form.SystemDataForm;

@Controller
@RequestMapping(value="/dataDictionaries/systemData")
public class SystemDatacontroller extends CommonController<SystemData, Integer, SystemDataForm> {
	
	@Autowired
	private SystemDataService dataService;
	
	@RequestMapping(value="/edit2")
	public String edit2(SystemDataForm form, ModelMap map, Integer id2) {
		SystemData model = new SystemData();
		Integer id = form.getId();
		if(id != null) {
			model = dataService.findById(id);
		}
		map.put("model", model);
		map.put("id", id2);
		return "dataDictionaries/systemdata/edit";
	}
	
	@RequestMapping(value="/manage2")
	public String manage2(ModelMap map, Integer id) {
		map.put("id", id);
		return "dataDictionaries/systemdata/manage";
	}

	@Override
	public Specification<SystemData> buildSpec(SystemDataForm form) {
		Specification<SystemData> specification = new Specification<SystemData>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<SystemData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				if(form.getDictionaries() != null) {
					Predicate name = cb.equal(root.get("dictionaries").get("id"), form.getDictionaries().getId());
					rules.add(name);
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}

		};
		return specification;
	}
	
}
