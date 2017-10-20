package com.thinkgem.jeesite.modules.oa.entity;

/**
 * Created by GQR on 2017/10/18.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.thinkgem.jeesite.modules.oa.helper.AttendanceHelper;

import java.util.List;

/**
 * 考勤内容-以月为单位
 *
 * @author mojun
 */

@Document(collection = "attendance")
public class AttendanceMonth {

	@Id
	private String id;

	// 姓名
	private String name;

	// 部门
	private String department;

	// 当前记录的年份
	private Integer year;

	// 当前记录的月份
	private Integer month;
	
    //考勤记录List（单位：月）
    @DBRef
    private List<AttendanceDay> attendanceStatus;

    private AttendanceDayStatus attendanceDayStatus;
    
    private AttendanceHelper attendanceHelper;

    //审批流程状态
    private String processStatus;
    
    public AttendanceMonth(){
    }
    
    public AttendanceMonth(List<AttendanceDay> list){
    	attendanceHelper = new AttendanceHelper(list);
    }

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public List<AttendanceDay> getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(List<AttendanceDay> attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public AttendanceHelper getAttendanceHelper() {
		return attendanceHelper;
	}

	public void setAttendanceHelper(AttendanceHelper attendanceHelper) {
		this.attendanceHelper = attendanceHelper;
	}

    public AttendanceDayStatus getAttendanceDayStatus() {
        return attendanceDayStatus;
    }

    public void setAttendanceDayStatus(AttendanceDayStatus attendanceDayStatus) {
        this.attendanceDayStatus = attendanceDayStatus;
    }
}