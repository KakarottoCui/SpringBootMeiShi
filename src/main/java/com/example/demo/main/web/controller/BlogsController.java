package com.example.demo.main.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

= new Specification<Blogs>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<Blogs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				SysUser user = userUtils.getUser();
				Predicate difficultylevel = cb.equal(root.get("user").get("id"), user.getId());
				rules.add(difficultylevel);
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}

		};
		return specification;
	}

	@RequestMapping(value="/page2")
	@ResponseBody
	public HashMap<String, Object> page2(ModelMap map, DataGridParam param, BlogsForm form) {
		Sort sort=Sort.by("id");
		Pageable pabeable = param.getPageable(sort);
		Specification<Blogs> spec = buildSpec1(form);
		Page<Blogs> page = blogService.findAll(spec, pabeable);
		return DataGridUtils.buildResult(page);
	}

	private Specification<Blogs> buildSpec1(BlogsForm form) {
		Specification<Blogs> specification = new Specification<Blogs>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<Blogs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				Predicate difficultylevel = cb.like(root.get("blogStatic"), "%待审核%");
				rules.add(difficultylevel);
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}

		};
		return specification;
	}
	
	/**
	 * 评论
	 */
	@RequestMapping(value="/comment")
	@ResponseBody
	public Object comment(String comment, Integer id) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Comment model = new Comment();
			model.setText(comment);
			model.setUser(userUtils.getUser());
			model.setBlog(blogService.findById(id));
			model.setRead2(false);
			model.setCreateDate(sdf.format(new Date()));
			commentService.save(model);
			return new AjaxResult("评论成功");
		} catch (Exception e) {
			return new AjaxResult(false,"评论失败");
		}
	}
}
