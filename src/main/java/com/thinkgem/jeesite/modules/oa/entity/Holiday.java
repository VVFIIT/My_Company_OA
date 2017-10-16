package com.thinkgem.jeesite.modules.oa.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 节假日
 */
public class Holiday {

	@Id
	private String id;

	// 假日名称
	private String name;

	// 假日日期
	private Date holiday;

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

	public Date getHoliday() {
		return holiday;
	}

	public void setHoliday(Date holiday) {
		this.holiday = holiday;
	}

}