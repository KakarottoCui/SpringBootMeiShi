package com.example.demo.main.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.custom.CommonService;
import com.example.demo.main.entity.SysFile;
import com.example.demo.main.repository.SysFileRepository;

@Service
public class SysFileService extends CommonService<SysFile, Integer> {
	@Autowired
	private SysFileRepository fileDAO;
	/** 图片存放位置 **/
	private final static String IMAGEPATH="d:\\blog\\image";
	/** 其他文件存放位置 **/
	private final static String OFFICEPATH="d:\\blog\\FileRecv";
	/** 视频文件存放位置 **/
	private final static String VIDEOPATH="d:\\blog\\video";
	
	/***
	 * 保存文件
	 * @param file
	 * @return 
	 * @throws IOException 
	 */
	@Transactional
	public boolean savefile(MultipartFile file ,String uuid){
		File path = path(file.getContentType());
		String filename = file.getOriginalFilename();
		SysFile fileEntity = new SysFile();
		fileEntity.setFileName(filename);
		fileEntity.setUuid(uuid);
		String storeaddress = path.getAbsolutePath();
		fileEntity.setIsUsed(false);
		fileEntity.setStoreaddress(storeaddress);
		if(file.getContentType().contains("image")) {
			fileEntity.setFileTypeName("图片文件");
		}else {
			fileEntity.setFileTypeName("其他文件");
		}
		File saveFile = new File(path,uuid);
		try {
			fileDAO.save(fileEntity);
			file.transferTo(saveFile);
			return true;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/***
	 * 查询文件是否为
	 * 新建文件夹
	 * @param filename
	 * @return 
	 */
	private File path(String filename) {
		File pat=new File("c:\\blog");
		File path=new File(SysFileService.IMAGEPATH);
		File paths=new File(SysFileService.OFFICEPATH);
		if(!pat.isDirectory()) {
			pat.mkdir();
		}
		if(!path.isDirectory()) {
			path.mkdir();
		}
		
		if(!paths.isDirectory()) {
			paths.mkdir();
		}
		if(filename.contains("image")) {
			return path;
		}else {
			return paths;
		}
	}
	
	/**
	 * 下载
	 * @param request
	 * @param fileName
	 * @return
	 */
	public void download(String uuid, HttpServletRequest request, HttpServletResponse response) {
		SysFile fileentity = fileDAO.findByUuid(uuid);
		String filename = fileentity.getFileName();
		filename = getStr(request, filename);
		File file = new File(fileentity.getStoreaddress(), uuid);
		if(file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				response.setContentType("application/x-msdownload");
				response.addHeader("Content-Disposition", "attachment; filename=" + filename );
				ServletOutputStream out = response.getOutputStream();
				byte[] buf=new byte[2048];
				int n=0;
				while((n=fis.read(buf))!=-1){
					out.write(buf, 0, n);
				}
				fis.close();
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getStr(HttpServletRequest request, String fileName) {
		String downloadFileName = null;
		//仅提供了部分代码，因为我们已经明确问题的所在，知道修改那一部分了，（代码中downloadFileName 即代表 *=utf-8'zh_cn'文件名.xx部分）
        String agent = request.getHeader("USER-AGENT");  
         try {
            	 if(agent != null && agent.toLowerCase().indexOf("firefox") > 0){
            		 //downloadFileName = "=?UTF-8?B?" + (new String(Base64Utils.encode(fileName.getBytes("UTF-8")))) + "?=";
            		 //设置字符集
            		 downloadFileName = "=?UTF-8?B?" + Base64Utils.encodeToString(fileName.getBytes("UTF-8")) + "?=";
            	 }else{
            		 downloadFileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
            	 }
		} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
        return downloadFileName;
	}
}
