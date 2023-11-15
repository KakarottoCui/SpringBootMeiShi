package com.example.demo.custom;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 公共实体类
 
 *
 * @param <ID>
 */
@MappedSuperclass
public class BaseEntity<ID> {
	private ID id;
	private Boolean isUsed; // 是否使用
	private String createName; // 创建人
	private String createDate; // 创建时间
	private String updateName;	//更新人
	private String updateDate;	//更新时间

	@Id
	@GeneratedValue
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
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

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public BaseEntity(ID id, Boolean isUsed, String createName, String createDate, String updateName,
			String updateDate) {
		super();
		this.id = id;
		this.isUsed = isUsed;
		this.createName = createName;
		this.createDate = createDate;
		this.updateName = updateName;
		this.updateDate = updateDate;
	}

	public BaseEntity() {
		super();
	}

	public BaseEntity(ID id) {
		super();
		this.id = id;
	}
}
