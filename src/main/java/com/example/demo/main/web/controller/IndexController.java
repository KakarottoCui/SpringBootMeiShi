package com.example.demo.main.web.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.main.entity.SysUser;
import com.example.demo.main.entity.TbMenu;
import com.example.demo.main.security.UserUtils;
import com.example.demo.main.service.MenuService;
import com.example.demo.main.service.SysFileService;
import com.example.demo.main.service.SysUserService;
import com.example.demo.main.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private SysFileService fileService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserUtils userUtils;
	@Autowired
	private SysUserService suserService;
	@Autowired
	private MenuService menuService;
	//静态路径
	@Value("${web.upload-path}")
	private String path;
	
	@RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
	public String index() {
		return "main/index";
	}
	
	@RequestMapping(value="/main")
	public String main(ModelMap map) {
		//加载菜单
		List<TbMenu> menus = menuService.findAuditMenu();
		map.put("menus", menus);
		if(menus.isEmpty()) {
			return "main/index";			
		}
		return "main/index1";
	}
	
	@RequestMapping(value="/system")
	public String system() {
		return "system/system";
	}
	
	@RequestMapping(value="/sy")
	public String sy() {
		return "main/sy";
	}
	
	@RequestMapping(value="/gy")
	public String gy() {
		return "main/gy";
	}
	
	@RequestMapping(value="/dataDictionaries")
	public String dataDictionaries() {
		return "dataDictionaries/dictionaries/manage";
	}
	
	/**
	 * 上传
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/upload")
	@ResponseBody
	private Object upload(MultipartFile file) {
		String uuid = UUID.randomUUID().toString();
		fileService.savefile(file, uuid);
		return path+"download?uuid="+uuid;
	}
	
	@RequestMapping(value="/gData")
	public Object gData(ModelMap map) {
		map.put("model", (userService.findById(userUtils.getUser().getId())));
		return "main/gData";
	}
	
	/**
	 * 下载
	 * @param uuid
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/download")
	@ResponseBody
	private void download(String uuid, HttpServletRequest request, HttpServletResponse response) {
		fileService.download(uuid, request, response);
	}
	
	@RequestMapping(value="/updataPassword")
	public Object updataPassword() {
		return "main/updataPassword";
	}
	
	/**
	 * 判断原密码是否正确
	 * */
	@RequestMapping(value="/pwd")
	@ResponseBody
	public Boolean pwd(String password) {
		SysUser user = userUtils.getUser();
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		boolean f = encoder.matches(password,user.getPassword());
		return f;
	}
	
	/****
	 * 修改密码
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/changePwd1")
	@ResponseBody
	public Boolean changePwd1(String password) {
		try {
			SysUser user = userUtils.getUser();
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(password));
			suserService.save(user);
			boolean f = true;
			return f;
		} catch (Exception e) {
			boolean f = false;
			return f;
		}
		
	}
	
}
