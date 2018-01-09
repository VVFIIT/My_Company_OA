package com.thinkgem.jeesite.modules.finance.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 招待费
 * 
 * @author Grace
 * @date 2018年1月5日 上午11:45:02
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class ReimburseHospitality extends DataEntity<ReimburseHospitality> {

	@Id
	private String id;
	private String mainId; // 主表主键
//	private String projectId; // 项目
	private String clientName; // 客户名称
	private String inviteesName;// 受邀人姓名
	private Date createDate;// 日期
	private String invitedPosition;// 受邀人职务
	private Integer number; // 人数
	private BigDecimal amount;// 金额
	private Date updateDate; // 更新时间

	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

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