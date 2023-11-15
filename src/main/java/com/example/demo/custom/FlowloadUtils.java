package com.example.demo.custom;

import java.util.HashMap;

import org.springframework.data.domain.Page;

public class FlowloadUtils {

	public static HashMap<String, Object> buildResult(Page page) {
		HashMap<String, Object> result=new HashMap<>();
		result.put("code", 0);
		result.put("msg","");
		result.put("pages", page.getTotalPages());
		result.put("data", page.getContent());
		return result;
	}
}
