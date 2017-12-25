package com.thinkgem.jeesite.modules.finance.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 出差申请
 * 
 * @author Grace
 * @date 2017年12月25日 下午3:26:28
 * @since 1.0.0
 */
public class BusinessTripApplication {

	@Id
	private String id;
	private String procInstId; // 流程相关Id
	private String officeId;// 部门
	private String applicantId;// 申请人
	private String projectId;// 长途车费
	private String togetherId; // 共同出差人
	private Date beginDate;// 出差开始日期
	private Date endDate;// 出差结束日期
	private String IDNo; // 身份证号
	private String remark;// 出差是由
	private String type;// 出差类型
	private String managerId; // 客户经理
	private String phone;// 电话
	private String status; // 状态
	private String managerFlag; // 经理是否同意
	private String FAFlag; // 财务是否同意
	private String ManagerComment;// 经理意见
	private String FAComment;// 财务意见

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getTogetherId() {
		return togetherId;
	}

	public void setTogetherId(String togetherId) {
		this.togetherId = togetherId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIDNo() {
		return IDNo;
	}

	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getManagerFlag() {
		return managerFlag;
	}

	public void setManagerFlag(String managerFlag) {
		this.managerFlag = managerFlag;
	}

	public String getFAFlag() {
		return FAFlag;
	}

	public void setFAFlag(String fAFlag) {
		FAFlag = fAFlag;
	}

	public String getManagerComment() {
		return ManagerComment;
	}

	public void setManagerComment(String managerComment) {
		ManagerComment = managerComment;
	}

	public String getFAComment() {
		return FAComment;
	}

	public void setFAComment(String fAComment) {
		FAComment = fAComment;
	}

}