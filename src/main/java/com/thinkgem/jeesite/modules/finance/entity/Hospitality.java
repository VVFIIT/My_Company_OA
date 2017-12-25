package com.thinkgem.jeesite.modules.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class Hospitality {

	@Id
	private String id;
	private String mainId; // 主表主键
	private String projectId; // 项目
	private String clientName; // 客户名称
	private String inviteesName;// 受邀人姓名
	private Date createDate;// 日期
	private String invitedPosition;// 受邀人职务
	private Integer number; // 人数
	private BigDecimal amount;// 金额
	private Date updateDate; // 更新时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getInviteesName() {
		return inviteesName;
	}

	public void setInviteesName(String inviteesName) {
		this.inviteesName = inviteesName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getInvitedPosition() {
		return invitedPosition;
	}

	public void setInvitedPosition(String invitedPosition) {
		this.invitedPosition = invitedPosition;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}