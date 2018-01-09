package com.thinkgem.jeesite.modules.finance.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseTaxi;

/**
 * @Author jjq
 * @Create 2018-01-03-11:04
 **/
@MyBatisDao
public interface ReimburseTaxiDao extends CrudDao<ReimburseTaxi> {

	/**
	 * 根据mainId查出租车费List
	 * 
	 * @param mainId
	 * @return
	 * @author Grace
	 * @date 2018年1月9日 下午4:25:43
	 */
	List<ReimburseTaxi> getTaxiListByMainId(String mainId);



}
