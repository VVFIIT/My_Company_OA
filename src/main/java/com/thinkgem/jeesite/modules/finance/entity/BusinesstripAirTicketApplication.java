package com.thinkgem.jeesite.modules.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 机票申请
 * 
 * @author Grace
 * @date 2017年12月25日 下午3:15:23
 * @since 1.0.0
 */
public class BusinesstripAirTicketApplication {

	@Id
	private String id;
	private String applicationId;// 出差申请主键
	private Date flyDate; // 出行时间
	private Date applyDate;// 申请日期',
	private BigDecimal amount;// 机票价格
	private String remark; // 申请理由
	private String startLocation;// 起始地
	private String arrivedLocation;// 目的地
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

	public Date getFlyDate() {
		return flyDate;
	}

	public void setFlyDate(Date flyDate) {
		this.flyDate = flyDate;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
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