package com.thinkgem.jeesite.modules.oa.entity;

import java.util.List;

/**
 * Created by GQR on 2017/10/18.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.oa.helper.AttendanceHelper;

/**
 * 考勤内容-以月为单位
 *
 * @author mojun
 */
@SuppressWarnings("serial")
@Document(collection = "attendance")
public class AttendanceMonth extends ActEntity<AttendanceMonth> {

	@Id
	private String id;

	// 姓名
	private String name;
	
	// 邮箱
	private String email;

	// 部门
	private String department;

	// 当前记录的年份
	private Integer year;

	// 当前记录的月份
	private Integer month;

	// 考勤记录List（单位：月）
	private List<AttendanceDay> attendanceStatus;

	private AttendanceDayStatus attendanceDayStatus;

	private AttendanceHelper attendanceHelper;

	// 审批流程状态
	private String processStatus;

	private String procInsId;

	private String PMComment;

	private String HRComment;

	public String getPMComment() {
		return PMComment;
	}

	public void setPMComment(String pMComment) {
		PMComment = pMComment;
	}

	public String getHRComment() {
		return HRComment;
	}

	public void setHRComment(String hRComment) {
		HRComment = hRComment;
	}

	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	public AttendanceMonth() {
		attendanceHelper = new AttendanceHelper();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ExcelField(title = "姓名", align = 2, sort = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title = "部门", align = 2, sort = 20)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@ExcelField(title = "年", align = 2, sort = 20)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@ExcelField(title = "月", align = 2, sort = 20)
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

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}