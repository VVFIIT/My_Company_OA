package com.thinkgem.jeesite.modules.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 特殊的日子（法定节假日和因为法定节假日串休的日子）
 * 
 * @author Grace
 * @date 2017年11月6日 下午5:28:06
 * @since 1.0.0
 */
public class LongDistance {

	@Id
	private String id;

	private String mainId; // 主表主键
	private String projectId; // 项目
	private String remark; // 摘要
	private BigDecimal amount;// 金额
	private Date createDate;// 创建时间
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

}