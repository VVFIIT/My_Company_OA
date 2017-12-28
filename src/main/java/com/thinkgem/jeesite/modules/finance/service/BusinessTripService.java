package com.thinkgem.jeesite.modules.finance.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.finance.dao.BusinessTripDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripReservation;


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
	
	@Transactional(readOnly = false)
	public void insertBusinessTripApplication(HttpServletRequest request) throws ParseException {
		BusinessTripApplication businessTripApplication = new BusinessTripApplication();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String togetherId = request.getParameter("togetherId");
		String phone = request.getParameter("phone");
		String IDNo = request.getParameter("IDNo");
		String projectId = request.getParameter("projectId");
		String type = request.getParameter("type");
		String remark = request.getParameter("remark");
		String begindate = request.getParameter(("beginDate"));
		String managerId = request.getParameter("managerId");
		businessTripApplication.setTogetherId(togetherId);
		businessTripApplication.setPhone(phone);
		businessTripApplication.setIDNo(IDNo);
		businessTripApplication.setProjectId(projectId);
		businessTripApplication.setType(type);
		businessTripApplication.setRemark(remark);
		if(begindate != null && !"".equals(begindate)) {
			businessTripApplication.setBeginDate(sdf.parse(begindate));
		}
		businessTripApplication.setManagerId(managerId);
		businessTripApplication.setId(UUID.randomUUID().toString());
		businessTripDao.insertBusinessTripApplication(businessTripApplication);
	}

	@Transactional(readOnly = false)
	public void insertBusinessTripReservation(HttpServletRequest request) {
		int reservationNum = Integer.valueOf(request.getParameter("reservationNum"));
		for(int i=1;i<reservationNum+1;i++) {
			BusinessTripReservation businessTripReservation = new BusinessTripReservation();
			String reservationType = request.getParameter(("reservationType"+i));
			String reservationCity = request.getParameter(("reservationCity"+i));
			String reservationWorkPlace = request.getParameter(("reservationWorkPlace"+i));
//			String reservationBeginDate = request.getParameter(("reservationBeginDate"+i));
//			String reservationEndDate = request.getParameter(("reservationEndDate"+i));
			businessTripReservation.setType(reservationType);
			businessTripReservation.setCity(reservationCity);
			businessTripReservation.setWorkPlace(reservationWorkPlace);
			businessTripDao.insertBusinessTripReservation(businessTripReservation);
		}
	}

	
}
