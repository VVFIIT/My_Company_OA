/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripReservation;
import com.thinkgem.jeesite.modules.finance.entity.Project;

/**
 * 报销
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:03:47
 * @since 1.0.0
 */
@MyBatisDao
public interface BusinessTripDao extends CrudDao<BusinessTripApplication> {

	public void insertBusinessTripApplication(BusinessTripApplication businessTripApplication);

	public void insertBusinessTripReservation(BusinessTripReservation businessTripReservation);

	public void insertBusinessTripAirTicket(BusinessTripAirTicket BusinessTripAirTicket);

	public Project getProjectByName(String projectName);

	
}
