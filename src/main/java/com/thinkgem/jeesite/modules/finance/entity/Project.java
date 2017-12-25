package com.thinkgem.jeesite.modules.finance.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 特殊的日子（法定节假日和因为法定节假日串休的日子）
 * 
 * @author Grace
 * @date 2017年11月6日 下午5:28:06
 * @since 1.0.0
 */
public class Project {

	@Id
	private String id;

	private String itemNo;// 项目编号

	private String name;// 项目名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}