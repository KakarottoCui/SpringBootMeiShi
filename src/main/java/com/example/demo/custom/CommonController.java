package com.example.demo.custom;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.main.security.UserUtils;

public class CommonController<T extends BaseEntity<ID>, ID, Form extends BaseForm<ID>> {
	@SuppressWarnings("unchecked")
	private Class<T> clazz=GenericsUtils.getSuperClassGenricType(getClass());
	
	@Autowired
	private CommonService<T, ID> baseService;
	@Autowired
	private UserUtils userUtils;
	
	@RequestMapping(value="manage")
	public void manage(ModelMap map) {
		
	}
	
	/**
	 * 新增修改页面
	 * @param form
	 * @param map
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value="/edit")
	public void edit(Form form, ModelMap map) throws InstantiationException, IllegalAccessException {
		T model = clazz.newInstance();
		ID id = form.getId();
		if(id != null) {
			model = baseService.findById(id);
		}
		map.put("model", model);
	}
	
	/**
	 * 保存方法
	 * @param form
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(Form form) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			T model = clazz.newInstance();
			//String name = userUtils.getName();
			ID id = form.getId();
			if(id != null) {
				model = baseService.findById(id);
				model.setUpdateDate(sdf.format(new Date()));
				//model.setUpdateName(name);
			}else {
				model.setCreateDate(sdf.format(new Date()));
				//model.setCreateName(name);
				model.setUpdateDate(sdf.format(new Date()));
				//model.setUpdateName(name);
			}
			BeanUtils.copyProperties(form, model,"id");
			baseService.save(model);
			return new AjaxResult("数据保存成功");
		} catch (Exception e) {
			return new AjaxResult(false,"数据保存失败");
		}
	}
	
	/**
	 * 删除
	 * @param form
	 * @return
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(Form form) {
		try {
			baseService.deleteById(form.getId());
			return new AjaxResult("数据删除成功");
		} catch (Exception e) {
			return new AjaxResult(false, "数据删除失败");
		}
	}
	
	/**
	 * 数据加载——bootstrap
	 * @param pageParam
	 * @param form
	 * @return
	 */
	@RequestMapping(value="/page")
	@ResponseBody
	public Object page(TablePageable pageParam, Form form) {
		PageRequest pageable = pageParam.bulidPageRequest();
		Specification<T> spec = buildSpec(form);
		Page<T> page=baseService.findAll(spec, pageable);
		return AjaxResult.bulidPageResult(page);
	}
	
	/**
	 * 数据加载——layui
	 * @param pageParam
	 * @param form
	 * @return
	 */
	@RequestMapping(value="/page1")
	@ResponseBody
	public Object page1(TablePageable pageParam, Form form) {
		PageRequest pageable = pageParam.bulidPageRequest();
		Specification<T> spec = buildSpec(form);
		Page<T> page=baseService.findAll(spec, pageable);
		return DataGridUtils.buildResult(page);
	}

	public Specification<T> buildSpec(Form form) {
		return null;
	}
	
	/**
	 * 查询页面
	 * @param form
	 * @param map
	 */
	@RequestMapping(value="/search")
	public void search(Form form, ModelMap map) {
		map.put("model", form);
	}
	
}
