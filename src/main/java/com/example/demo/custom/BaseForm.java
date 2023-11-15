package com.example.demo.custom;

/**
 * 公共form
 
 *
 * @param <ID>
 */
public  class BaseForm<ID> {
	private ID id;
	private Boolean isUsed;
	private String createName; // 创建人
	private String createDate; // 创建时间
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
