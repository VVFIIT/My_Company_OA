package com.thinkgem.jeesite.modules.finance.helper;

import java.util.List;

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
public class ReimburseModel {

	private ReimburseMain reimbursementMain;
	private List<ReimburseHospitality> hospitalityList;
	private List<ReimburseLongDistance> longDistanceList;
	private List<ReimburseOther> otherList;
	private List<ReimburseTaxi> taxiList;

	public ReimburseMain getReimbursementMain() {
		return reimbursementMain;
	}

	public void setReimbursementMain(ReimburseMain reimbursementMain) {
		this.reimbursementMain = reimbursementMain;
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

}
