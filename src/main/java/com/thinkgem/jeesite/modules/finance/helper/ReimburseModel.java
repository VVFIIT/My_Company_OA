package com.thinkgem.jeesite.modules.finance.helper;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseHospitality;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseLongDistance;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseOther;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseTaxi;

/**
 * 报销Model
 * 
 * @author Grace
 * @date 2017年12月26日 下午3:13:13
 * @since 1.0.0
 */
public class ReimburseModel extends ActEntity<ReimburseModel> {

	// private ReimburseMain reimbursementMain;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097802654149429818L;
	// main表
	private String status;
	private String id;
	private Date applyDate;// 申请日期
	private Date beginDate;//
	private Date endDate; //
	private String remark; //
	private String totalAmount; // 合计金额',

	private String officeName;
	private String userName;
	private List<ReimburseHospitality> hospitalityList;
	private List<ReimburseLongDistance> longDistanceList;
	private List<ReimburseOther> otherList;
	private List<ReimburseTaxi> taxiList;

	private String longDistanceEveryNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLongDistanceEveryNum() {
		return longDistanceEveryNum;
	}

	public void setLongDistanceEveryNum(String longDistanceEveryNum) {
		this.longDistanceEveryNum = longDistanceEveryNum;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ReimburseHospitality> getHospitalityList() {
		return hospitalityList;
	}

	public void setHospitalityList(List<ReimburseHospitality> hospitalityList) {
		this.hospitalityList = hospitalityList;
	}

	public List<ReimburseLongDistance> getLongDistanceList() {
		return longDistanceList;
	}

	public void setLongDistanceList(List<ReimburseLongDistance> longDistanceList) {
		this.longDistanceList = longDistanceList;
	}

	public List<ReimburseOther> getOtherList() {
		return otherList;
	}

	public void setOtherList(List<ReimburseOther> otherList) {
		this.otherList = otherList;
	}

	public List<ReimburseTaxi> getTaxiList() {
		return taxiList;
	}

	public void setTaxiList(List<ReimburseTaxi> taxiList) {
		this.taxiList = taxiList;
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
