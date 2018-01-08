package com.thinkgem.jeesite.modules.finance.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 出差申请
 * 
 * @author Grace
 * @date 2017年12月25日 下午3:26:28
 * @since 1.0.0
 */
public class BusinessTripApplication extends ActEntity<BusinessTripApplication>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String procInstId; // 流程相关Id
	private Office office;// 部门
	private User applicant;// 申请人
	private Project project;// 项目Id
	private User together; // 共同出差人
	private Date beginDate;// 出差开始日期
	private Date endDate;// 出差结束日期
	private String IDNo; // 身份证号
	private String remark;// 出差是由
	private String type;// 出差类型
	private User manager; // 客户经理
	private String phone;// 电话
	private String status; // 状态
	private String managerFlag; // 经理是否同意
	private String FAFlag; // 财务是否同意
	private String managerComment;// 经理意见
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


	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getTogether() {
		return together;
	}

	public void setTogether(User together) {
		this.together = together;
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

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
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
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}

	public String getFAComment() {
		return FAComment;
	}

	public void setFAComment(String fAComment) {
		FAComment = fAComment;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	

}