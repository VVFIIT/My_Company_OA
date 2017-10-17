package com.thinkgem.jeesite.modules.oa.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * 考勤
 */
public class Attendance {

	@Id
	private UUID id;

	private String name;

	// 年
	private String year;

	// 月份
	private String month;

	// 日期
	private String date;

	// 日期
	private String week;

	// 工作城市
	private String city;

	// 考勤状态
	private String attStatus;

	// 删除标记
	private String del_flag;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID uuid) {
		this.id = uuid;
	}

	public String getName() {
		return name;
	}
}