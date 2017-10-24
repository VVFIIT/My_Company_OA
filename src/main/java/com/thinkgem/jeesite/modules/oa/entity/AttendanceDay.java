package com.thinkgem.jeesite.modules.oa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * Created by Jiang on 2017/10/18.
 */
@Document
public class AttendanceDay {

    //几月几号的号
    private Integer date;

    //星期几
    private String week;

    //工作地点
    private String location;

    //考勤状态
    private String status;

    @ExcelField(title="日期", align=2, sort=0)
	public Integer getDate() {
		return date;
	}


	public void setDate(Integer date) {
		this.date = date;
	}

	@ExcelField(title="星期", align=2, sort=40)
	public String getWeek() {
		return week;
	}


	public void setWeek(String week) {
		this.week = week;
	}

	@ExcelField(title="工作地点", align=2, sort=80)
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	@ExcelField(title="考勤状态", align=2, sort=100)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
