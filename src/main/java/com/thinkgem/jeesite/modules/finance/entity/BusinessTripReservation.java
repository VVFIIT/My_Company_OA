package com.thinkgem.jeesite.modules.finance.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 出差申请
 * 
 * @author Grace
 * @date 2017年12月25日 下午3:46:14
 * @since 1.0.0
 */
public class BusinessTripReservation {

	@Id
	private String id;
	private String applicationId;// 申请单Id
	private String type;// 订房类型
	private String city;// 出差城市
	private String workPlace;// 出差具体工作地点
	private Date beginDate;// 入住日期
	private String endDate;// 退房日期
	private String remark;// 出差是由
	private Date createDate;// 创建时间
	private Date updateDate;// 更新时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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