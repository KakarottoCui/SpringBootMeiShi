package com.example.demo.main.entity;

import javax.persistence.Entity;

import com.example.demo.custom.BaseEntity;

/**
 * 图片文件表
 
 *
 */
@Entity
public class SysFile extends BaseEntity<Integer> {
	private String fileName;	//文件名称
	private String fileTypeName;	//文件类型名称
	private String storeaddress;	//文件存放地址
	private String uuid;	//图片的uuid
	private String resourceprofile;		//资源简介
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileTypeName() {
		return fileTypeName;
	}
	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getResourceprofile() {
		return resourceprofile;
	}
	public void setResourceprofile(String resourceprofile) {
		this.resourceprofile = resourceprofile;
	}
	public String getStoreaddress() {
		return storeaddress;
	}
	public void setStoreaddress(String storeaddress) {
		this.storeaddress = storeaddress;
	}
}
