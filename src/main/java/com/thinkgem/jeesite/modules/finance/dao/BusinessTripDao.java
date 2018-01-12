/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripHotel;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripReservation;
import com.thinkgem.jeesite.modules.finance.entity.Project;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 出差
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
	
	public void insertBusinessTripHotel(BusinessTripHotel businessTripHotel);

	public Project getProjectByName(String projectName);
	
	public User getUserByName(String userName);

	public void updateProcInsIdByApplicationId(String procInsId, String applicationId);

	public String findTaskIdByProcInsId(String procInsId, String noteID);

	public void updateStatus(String status, String applicationId);

	public BusinessTripApplication getBusinessTripApplicationInfo(String id);

	public List<BusinessTripReservation> getBusinessTripReservationList(String applicationId);
	
	public BusinessTripReservation getBusinessTripReservation(String id);
	
	public List<BusinessTripAirTicket> getBusinessTripAirTicketList(String applicationId);
	
	public BusinessTripHotel getBusinessTripHotel(String reservationId);

	public void updateManagerApproveInfo(String managerFlag, String managerComment, String applicationId);

	public void updateFAApproveInfo(String fAFlag, String fAComment, String applicationId);

	public List<String> getBusinessTripProjectNameList();

	public void updateBusinessTripReservationType(String type, String id);

	public void updateBusinessTripApplication(BusinessTripApplication businessTripApplication);

	public void deleteBusinessTripReservation(String businessTripApplicationId);
	
	public void deleteBusinessTripInsertReservation(String businessTripApplicationId);

	public void deleteBusinessTripAirTicket(String businessTripApplicationId);
	
	public void deleteBusinessInsertTripAirTicket(String businessTripApplicationId);

	public List<BusinessTripReservation> getBusinessTripReservationInsertList(String id);

	public List<BusinessTripAirTicket> getBusinessTripAirTicketInsertList(String id);

	public List<BusinessTripReservation> getBusinessTripReservationNoInsertList(String id);

	public List<BusinessTripAirTicket> getBusinessTripAirTicketNoInsertList(String id);

	public void updateReservationInsertFlag(String id);

	public void updateAirTicketInsertFlag(String id);

	public void updateHotelInsertFlag(String id);

	public void deleteBusinessTripApplication(String id);

	

	

	
	
}
