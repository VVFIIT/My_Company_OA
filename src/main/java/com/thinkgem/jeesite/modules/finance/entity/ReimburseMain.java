package com.thinkgem.jeesite.modules.finance.entity;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.data.annotation.Id;

/**
 * 报销主单
 * 
 * @author Grace
 * @date 2018年1月5日 上午11:44:45
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class ReimburseMain extends DataEntity<ReimburseMain> {

	@Id
	private String id;
	private String procInstId;// 流程相关Id
	private String officeId;// 部门Id
	private String applicantId;// 申请人
	private String status;// 状态 10创建/20已提交/30财务已审批通过/40驳回/50领导审批通过
	private Date applyDate;// 申请日期
	private Date beginDate;//
	private Date endDate; //
	private String remark; //
	private String totalAmount; // 合计金额',
	private Date updateDate;// 更新时间
	private String managerFlag;// 经理是否同意
	private String FAFlag;// 财务是否同意',
	private String managerComment; // 经理意见
	private String FAComment; // 财务意见

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

}