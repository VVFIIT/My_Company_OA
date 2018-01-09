package com.thinkgem.jeesite.modules.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.thinkgem.jeesite.common.persistence.BaseEntity;

/**
 * 出租车费
 * 
 * @author Grace
 * @date 2018年1月5日 上午11:45:27
 * @since 1.0.0
 */
public class ReimburseTaxi extends BaseEntity<ReimburseTaxi> {

	@Id
	private String id;
	private String mainId; // 主表主键
	// private String projectId; // 项目
	private Project project;
	private String remark; //
	private BigDecimal amount; // 合计金额',
	private Date createDate;
	private String time;// 时间
	private String departureLocation; // 出发地点
	private String arrivedLocation; // 到达地点
	private Date updateDate;// 更新时间

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

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getArrivedLocation() {
		return arrivedLocation;
	}

	public void setArrivedLocation(String arrivedLocation) {
		this.arrivedLocation = arrivedLocation;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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