package com.thinkgem.jeesite.modules.finance.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 出租车费
 * 
 * @author Grace
 * @date 2018年1月5日 上午11:45:27
 * @since 1.0.0
 */
public class ReimburseTaxi {

	@Id
	private String id;
	private String mainId; // 主表主键
	private String projectId; // 项目
	private String remark; //
	private String amount; // 合计金额',
	private Date createDate;
	private Date beginTime;// 开始时间
	private Date endTime; // 结束时间
	private String departureLocation; // 出发地点
	private String arrivedLocation; // 到达地点
	private Date updateDate;// 更新时间

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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

}