package com.thinkgem.jeesite.modules.oa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

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

    //getter and setter
    public Integer getdate() {
        return date;
    }

    public void setdate(Integer date) {
        this.date = date;
    }

    public String getweek() {
        return week;
    }

    public void setweek(String week) {
        this.week = week;
    }

    public String getlocation() {
        return location;
    }

    public void setlocation(String location) {
        this.location = location;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }
}
