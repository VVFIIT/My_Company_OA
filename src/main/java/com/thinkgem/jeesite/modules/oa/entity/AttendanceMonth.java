package com.thinkgem.jeesite.modules.oa.entity;

/**
 * Created by GQR on 2017/10/18.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 考勤内容-以月为单位
 *
 * @author mojun
 */

@Document(collection="attendance")
public class AttendanceMonth {

    @Id
    private String id;

    //姓名
    private String name;

    //部门
    private String department;

    //当前记录的年份
    private Integer year;

    //当前记录的月份
    private Integer month;

    //考勤记录List（单位：月）
    @DBRef
    private List<AttendanceDay> attendanceStatus;

    //审批流程状态
    private String processStatus;


    //getter and setter
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdepartment() {
        return department;
    }

    public void setdepartment(String department) {
        this.department = department;
    }

    public Integer getyear() {
        return year;
    }

    public void setyear(Integer year) {
        this.year = year;
    }

    public Integer getmonth() {
        return month;
    }

    public void setmonth(Integer month) {
        this.month = month;
    }

    public List<AttendanceDay> getattendanceStatus() {
        return attendanceStatus;
    }

    public void setattendanceStatus(List<AttendanceDay> attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getprocessStatus() {
        return processStatus;
    }

    public void setprocessStatus(String processStatus) {
        this.processStatus = processStatus;
    }
}