package com.thinkgem.jeesite.modules.finance.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.finance.dao.BusinessTripDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripReservation;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
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
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly = false)
	public void insertBusinessTripApplication(HttpServletRequest request) throws ParseException {
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
//		User togetherUser = new User();
//		togetherUser.setName(togetherName);
//		businessTripApplication.setTogetherId(userDao.get(togetherUser).getId());
		businessTripApplication.setTogetherId("134b1ea7caa64112962b97db8b4ad2a7");
		businessTripApplication.setPhone(phone);
		businessTripApplication.setIDNo(IDNo);
//		String projectId = businessTripDao.getProjectByName(projectName).getId();
		businessTripApplication.setProjectId("1");
		businessTripApplication.setType(type);
		businessTripApplication.setRemark(remark);
		if(begindate != null && !"".equals(begindate)) {
			businessTripApplication.setBeginDate(sdf.parse(begindate));
		}
//		User managerUser = new User();
//		managerUser.setName(managerName);
		businessTripApplication.setManagerId("4d07105c85a7427ea477cbdb0acb51ed");
		businessTripApplication.setId(UUID.randomUUID().toString());
		User user = UserUtils.getUser();
		businessTripApplication.setOfficeId(user.getOffice().getId());
		businessTripApplication.setApplicantId(user.getId());
		businessTripDao.insertBusinessTripApplication(businessTripApplication);
	}

	@Transactional(readOnly = false)
	public void insertBusinessTripReservation(HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		List<BusinessTripReservation> list1 = new ArrayList<BusinessTripReservation>();
		int reservationNum = Integer.valueOf(request.getParameter("reservationNum"));
		for(int i=1;i<reservationNum+1;i++) {
			BusinessTripReservation businessTripReservation = new BusinessTripReservation();
			String reservationType = request.getParameter(("reservationType"+i));
			String reservationCity = request.getParameter(("reservationCity"+i));
			String reservationWorkPlace = request.getParameter(("reservationWorkPlace"+i));
			String reservationBeginDate = request.getParameter(("reservationBeginDate"+i));
			String reservationEndDate = request.getParameter(("reservationEndDate"+i));
			String reservationDays = request.getParameter(("reservationDays"+i));
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
			list1.add(businessTripReservation);
//			businessTripDao.insertBusinessTripReservation(businessTripReservation);
		}
	}

	public void insertBusinessTripAirTicket(HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<BusinessTripAirTicket> list2 = new ArrayList<BusinessTripAirTicket>();
		int airTicketNum = Integer.valueOf(request.getParameter("airTicketNum"));
		for(int i=1;i<airTicketNum+1;i++) {
			BusinessTripAirTicket businessTripAirTicket = new BusinessTripAirTicket();
			String airTicketFlyDate = request.getParameter(("airTicketFlyDate"+i));
			String airTicketAmount = request.getParameter(("airTicketAmount"+i));
			String airTicketStartLocation = request.getParameter(("airTicketStartLocation"+i));
			String airTicketArrivedLocation = request.getParameter(("airTicketArrivedLocation"+i));
			String airTicketRemark = request.getParameter(("airTicketRemark"+i));
			if(airTicketFlyDate != null && !"".equals(airTicketFlyDate)) {
				businessTripAirTicket.setFlyDate(sdf.parse(airTicketFlyDate));
			}
			if(airTicketAmount != null && !"".equals(airTicketAmount)) {
				businessTripAirTicket.setAmount(new BigDecimal(airTicketAmount));
			}
			businessTripAirTicket.setStartLocation(airTicketStartLocation);
			businessTripAirTicket.setArrivedLocation(airTicketArrivedLocation);
			businessTripAirTicket.setRemark(airTicketRemark);
			list2.add(businessTripAirTicket);
//			businessTripDao.insertBusinessTripAirTicket(businessTripAirTicket);
		}
	}

	
}
