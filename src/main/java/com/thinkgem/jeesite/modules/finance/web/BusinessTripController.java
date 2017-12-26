/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripModel;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripReservation;

/**
 * 出差
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:05:19
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "${adminPath}/fa/businessTrip")
public class BusinessTripController extends BaseController {

	@ModelAttribute("businessTripModel")
	public BusinessTripModel getBusinessTripModel() {
		return new BusinessTripModel();
	}
	
	@RequestMapping(value = "toApplyForm")
	public String toApply(BusinessTripModel businessTripModel, Model model) {
		BusinessTripApplication businessTripApplication = new BusinessTripApplication();
		List<BusinessTripReservation> businessTripReservationList = new ArrayList<BusinessTripReservation>();
		List<BusinessTripAirTicket> businessTripAirTicketList = new ArrayList<BusinessTripAirTicket>();
		businessTripApplication.setApplicantId("苗群");
		businessTripApplication.setOfficeId("研发一部");
		for(int i=0;i<2;i++) {
			BusinessTripReservation businessTripReservation = new BusinessTripReservation();
			BusinessTripAirTicket businessTripAirTicket = new BusinessTripAirTicket();
			businessTripReservationList.add(businessTripReservation);
			businessTripAirTicketList.add(businessTripAirTicket);
		}
		businessTripModel.setBusinessTripReservationList(businessTripReservationList);
		businessTripModel.setBusinessTripAirTicketList(businessTripAirTicketList);
		businessTripModel.setBusinessTripApplication(businessTripApplication);
		model.addAttribute("businessTripModel", businessTripModel);
		return "modules/fa/businessTripApplyForm";
	}
	
	@RequestMapping(value = "commitApplyForm")
	public String commitApplyForm(BusinessTripModel businessTripModel, Model model) {
		return "modules/fa/businessTripApplyForm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
