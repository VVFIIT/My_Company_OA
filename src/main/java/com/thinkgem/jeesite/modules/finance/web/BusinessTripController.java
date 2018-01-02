/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.web;

import java.text.ParseException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripModel;
import com.thinkgem.jeesite.modules.finance.service.BusinessTripService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

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
	
	/**
	 * 进入出差申请页面
	 * @author Meng
	 */
	@RequestMapping(value = "toApplyForm")
	public String toApply(BusinessTripModel businessTripModel, Model model) {
		User user = UserUtils.getUser();
		model.addAttribute("applicantName", user.getName());
		model.addAttribute("officeName", user.getOffice().getName());
		return "modules/fa/businessTripApplyForm";
	}
	
	/**
	 * 出差申请页面提交
	 * @author Meng
	 */
	@RequestMapping(value = "commitApplyForm")
	public String commitApplyFormSoncend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String applicationId = UUID.randomUUID().toString();
		try {
			businessTripService.insertBusinessTripApplication(request, applicationId);
			businessTripService.insertBusinessTripReservation(request, applicationId);
			businessTripService.insertBusinessTripAirTicket(request, applicationId);
			addMessage(redirectAttributes, "出差申请成功!");
		} catch (ParseException e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "出差申请失败!");
		}
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toApplyForm?repage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
