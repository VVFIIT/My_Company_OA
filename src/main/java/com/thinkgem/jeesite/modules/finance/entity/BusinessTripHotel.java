package com.thinkgem.jeesite.modules.finance.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 订房信息
 * 
 * @author Grace
 * @date 2017年12月25日 下午3:39:07
 * @since 1.0.0
 */
public class BusinessTripHotel {

	@Id
	private String id;
	private String applicationId;// 申请单Id
	private String hotel;// 酒店名
	private String address;// 酒店地址
	private String contact;// 酒店联系人
	private String contactPhone;// 联系电话
	private String staff;// 住宿员工
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

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
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