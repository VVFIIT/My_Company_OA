/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.web;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripModel;
import com.thinkgem.jeesite.modules.finance.service.BusinessTripService;

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
	
	@Autowired
	private BusinessTripService businessTripService;

	@ModelAttribute("businessTripModel")
	public BusinessTripModel getBusinessTripModel() {
		return new BusinessTripModel();
	}
	
	@RequestMapping(value = "toApplyForm")
	public String toApply(BusinessTripModel businessTripModel, Model model) throws ParseException {
		BusinessTripApplication businessTripApplication = new BusinessTripApplication();
		businessTripApplication.setApplicantId("苗群");
		businessTripApplication.setOfficeId("研发一部");
		businessTripModel.setBusinessTripApplication(businessTripApplication);
		model.addAttribute("businessTripModel", businessTripModel);
		return "modules/fa/businessTripApplyForm";
	}
	
	@RequestMapping(value = "commitApplyForm")
	public String commitApplyForm(HttpServletRequest request) throws ParseException {
		businessTripService.insertBusinessTripApplication(request);
		businessTripService.insertBusinessTripReservation(request);
		return "modules/fa/businessTripApplyForm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
