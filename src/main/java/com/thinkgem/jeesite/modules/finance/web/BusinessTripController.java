/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripModel;

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
		
		return "modules/fa/businessTripApplyForm";
	}
}
