/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.web;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
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
	
	@ModelAttribute("businessTripApplication")
	public BusinessTripApplication getBusinessTripApplication() {
		return new BusinessTripApplication();
	}
	
	/**
	 * 进入出差申请页面
	 * @author Meng
	 */
	@RequestMapping(value = "toApplyForm")
	public String toApply(Model model) {
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
			String procInsId = businessTripService.startBusinessTripProcess(applicationId);
			businessTripService.completeApplicantProcess(procInsId, applicationId);
			addMessage(redirectAttributes, "出差申请成功!");
		} catch (ParseException e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "出差申请失败!");
		}
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toApplyForm?repage";
	}
	
	/**
	 * 出差任务列表
	 * @author Meng
	 */
	@RequestMapping(value = "toBusinessTripTaskList")
	public String toBusinessTripTaskList(BusinessTripApplication businessTripApplication, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<BusinessTripApplication> list = businessTripService.businessTripTaskList();
		Page<BusinessTripApplication> page = businessTripService.findPage(new Page<BusinessTripApplication>(request, response), list);
		model.addAttribute("page", page);
		return "modules/fa/businessTripTaskList";
	}
	
	/**
	 * 进入出差信息更新页面
	 * @author Meng
	 */
	@RequestMapping(value = "toupdateBusinessTripInfo")
	public String toupdateBusinessTripInfo() {
		
		return "modules/fa/businessTripUpdateForm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
