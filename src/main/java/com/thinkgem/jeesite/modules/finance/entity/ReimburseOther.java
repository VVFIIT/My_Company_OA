package com.thinkgem.jeesite.modules.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.thinkgem.jeesite.common.persistence.BaseEntity;

/**
 * 其他费用
 * 
 * @author Grace
 * @date 2018年1月5日 上午11:44:34
 * @since 1.0.0
 */
public class ReimburseOther extends BaseEntity<ReimburseOther> {

	@Id
	private String id;

	private String mainId; // 主表主键
	// private String projectId; // 项目
	private Project project;
	private String remark; // 摘要
	private BigDecimal amount;// 金额
	private Date createDate;// 创建时间
	private Date updateDate; // 更新时间

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub

	}

}