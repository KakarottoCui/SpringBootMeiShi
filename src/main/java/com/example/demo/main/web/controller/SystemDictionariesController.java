package com.example.demo.main.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.custom.CommonController;
import com.example.demo.main.entity.SystemDictionaries;
import com.example.demo.main.web.form.SystemDictionariesForm;

@Controller
@RequestMapping(value="/dataDictionaries/dictionaries")
public class SystemDictionariesController extends CommonController<SystemDictionaries, Integer, SystemDictionariesForm> {
	
}
