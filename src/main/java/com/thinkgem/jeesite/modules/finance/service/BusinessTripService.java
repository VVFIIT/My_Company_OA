package com.thinkgem.jeesite.modules.finance.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.finance.dao.BusinessTripDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripReservation;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;



/**
 * 出差
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:04:37
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BusinessTripService {
	
	@Autowired
	private BusinessTripDao businessTripDao;
	
	/**
	 * 将出差信息插入db
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void insertBusinessTripApplication(HttpServletRequest request, String applicationId) throws ParseException {
		BusinessTripApplication businessTripApplication = new BusinessTripApplication();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String togetherName = request.getParameter("togetherId");
		String phone = request.getParameter("phone");
		String IDNo = request.getParameter("IDNo");
		String projectName = request.getParameter("projectId");
		String type = request.getParameter("type");
		String remark = request.getParameter("remark");
		String begindate = request.getParameter(("beginDate"));
		String managerName = request.getParameter("managerId");
		businessTripApplication.setTogetherId(businessTripDao.getUserByName(togetherName).getId());
		businessTripApplication.setPhone(phone);
		businessTripApplication.setIDNo(IDNo);
		businessTripApplication.setProjectId(businessTripDao.getProjectByName(projectName).getId());
		businessTripApplication.setType(type);
		businessTripApplication.setRemark(remark);
		if(begindate != null && !"".equals(begindate)) {
			businessTripApplication.setBeginDate(sdf.parse(begindate));
		}
		businessTripApplication.setManagerId(businessTripDao.getUserByName(managerName).getId());
		businessTripApplication.setId(applicationId);
		User user = UserUtils.getUser();
		businessTripApplication.setOfficeId(user.getOffice().getId());
		businessTripApplication.setApplicantId(user.getId());
		businessTripDao.insertBusinessTripApplication(businessTripApplication);
	}

	/**
	 * 将订房信息插入db
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void insertBusinessTripReservation(HttpServletRequest request, String applicationId) throws ParseException {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String reservationEveryNum = request.getParameter("reservationEveryNum");
		String[] reservationNum = reservationEveryNum.split("");
		for (int i = 0; i < reservationNum.length; i++) {
			BusinessTripReservation businessTripReservation = new BusinessTripReservation();
			String reservationType = request.getParameter(("reservationType"+reservationNum[i]));
			String reservationCity = request.getParameter(("reservationCity"+reservationNum[i]));
			String reservationWorkPlace = request.getParameter(("reservationWorkPlace"+reservationNum[i]));
			String reservationBeginDate = request.getParameter(("reservationBeginDate"+reservationNum[i]));
			String reservationEndDate = request.getParameter(("reservationEndDate"+reservationNum[i]));
			String reservationDays = request.getParameter(("reservationDays"+reservationNum[i]));
			businessTripReservation.setId(UUID.randomUUID().toString());
			businessTripReservation.setApplicationId(applicationId);
			businessTripReservation.setType(reservationType);
			businessTripReservation.setCity(reservationCity);
			businessTripReservation.setWorkPlace(reservationWorkPlace);
			if(reservationBeginDate != null && !"".equals(reservationBeginDate)) {
				businessTripReservation.setBeginDate(sdf.parse(reservationBeginDate));
			}
			if(reservationEndDate != null && !"".equals(reservationEndDate)) {
				businessTripReservation.setEndDate(sdf.parse(reservationEndDate));
			}
			if(reservationDays != null && !"".equals(reservationDays)) {
				businessTripReservation.setDays(Integer.valueOf(reservationDays));
			}
			businessTripDao.insertBusinessTripReservation(businessTripReservation);
		}
	}

	/**
	 * 将机票信息插入db
	 * @author Meng
	 */
	@Transactional(readOnly = false)
	public void insertBusinessTripAirTicket(HttpServletRequest request, String applicationId) throws ParseException {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String airTicketEveryNum = request.getParameter("airTicketEveryNum");
		String[] airTicketNum = airTicketEveryNum.split("");
		for (int i = 0; i < airTicketNum.length; i++) {
			BusinessTripAirTicket businessTripAirTicket = new BusinessTripAirTicket();
			String airTicketFlyDate = request.getParameter(("airTicketFlyDate"+airTicketNum[i]));
			String airTicketAmount = request.getParameter(("airTicketAmount"+airTicketNum[i]));
			String airTicketStartLocation = request.getParameter(("airTicketStartLocation"+airTicketNum[i]));
			String airTicketArrivedLocation = request.getParameter(("airTicketArrivedLocation"+airTicketNum[i]));
			String airTicketRemark = request.getParameter(("airTicketRemark"+airTicketNum[i]));
			businessTripAirTicket.setId(UUID.randomUUID().toString());
			businessTripAirTicket.setApplicationId(applicationId);
			if(airTicketFlyDate != null && !"".equals(airTicketFlyDate)) {
				businessTripAirTicket.setFlyDate(sdf.parse(airTicketFlyDate));
			}
			if(airTicketAmount != null && !"".equals(airTicketAmount)) {
				businessTripAirTicket.setAmount(new BigDecimal(airTicketAmount));
			}
			businessTripAirTicket.setStartLocation(airTicketStartLocation);
			businessTripAirTicket.setArrivedLocation(airTicketArrivedLocation);
			businessTripAirTicket.setRemark(airTicketRemark);
			businessTripDao.insertBusinessTripAirTicket(businessTripAirTicket);
		}
	}

	
}
