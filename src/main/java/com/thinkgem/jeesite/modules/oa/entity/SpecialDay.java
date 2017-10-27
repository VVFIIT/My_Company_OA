package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 特殊的日子（法定节假日和因为法定节假日串休的日子）
 */
public class SpecialDay {

	@Id
	private String id;

	// 假日名称
	private String name;

	// 类型（special/holiday）
	private String type;

	// 日期
	private Date Date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

}