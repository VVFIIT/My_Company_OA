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
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripAirTicket;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripApplication;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripHotel;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripHotelHelper;
import com.thinkgem.jeesite.modules.finance.entity.BusinessTripReservation;
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

	@ModelAttribute("businessTripHotelHelper")
	public BusinessTripHotelHelper getBusinessTripHotelHelper() {
		return new BusinessTripHotelHelper();
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
		List<String> projectNameList = businessTripService.getBusinessTripProjectNameList();
		model.addAttribute("projectNameList", projectNameList);
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
	public String toBusinessTripTaskList(BusinessTripApplication businessTripApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BusinessTripApplication> list = businessTripService.businessTripTaskList(businessTripApplication);
		Page<BusinessTripApplication> page = businessTripService.findPage(new Page<BusinessTripApplication>(request, response), list);
		model.addAttribute("page", page);
		model.addAttribute("loginName", UserUtils.getUser().getLoginName());
		List<String> projectNameList = businessTripService.getBusinessTripProjectNameList();
		model.addAttribute("projectNameList", projectNameList);
		return "modules/fa/businessTripTaskList";
	}
	
	/**
	 * 进入出差信息更新页面
	 * 未完成
	 * @author Meng
	 */
	@RequestMapping(value = "toupdateBusinessTripInfo")
	public String toupdateBusinessTripInfo(String id, Model model) {
		BusinessTripApplication businessTripApplication = businessTripService.getBusinessTripApplicationInfo(id);
		List<BusinessTripReservation> businessTripReservationList = businessTripService.getBusinessTripReservationList(id);
		model.addAttribute("reservationEveryNum", businessTripReservationList.size());
		model.addAttribute("businessTripReservationList", businessTripReservationList);
		model.addAttribute("businessTripApplication", businessTripApplication);
		return "modules/fa/businessTripUpdateForm";
	}
	
	/**
	 * 提交更新出差信息
	 * 未完成
	 * @author Meng
	 */
	@RequestMapping(value = "updateBusinessTripInfo")
	public String updateBusinessTripInfo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripTaskList?repage";
	}
	
	/**
	 * 进入出差审批页面--经理
	 * @author Meng
	 */
	@RequestMapping(value = "toApproveBusinessTripInfo_Manager")
	public String toApproveBusinessTripInfo_Manager(String id, Model model) {
		BusinessTripApplication businessTripApplication = new BusinessTripApplication();
		businessTripApplication.setId(id);
		String name = businessTripService.getBusinessTripApplicationInfo(id).getApplicant().getName();
		businessTripApplication.setManagerComment("同意"+name+"的出差申请。");
		model.addAttribute("businessTripApplication", businessTripApplication);
		return "modules/fa/businessTripManagerApprove";
	}
	
	/**
	 * 出差审批--经理
	 * @author Meng
	 */
	@RequestMapping(value = "approveBusinessTripInfo_Manager")
	public String approveBusinessTripInfo_Manager(BusinessTripApplication businessTripApplication, RedirectAttributes redirectAttributes) {
		businessTripService.managerApprove(businessTripApplication);
		businessTripService.completeManagerProcess(businessTripApplication.getId());
		addMessage(redirectAttributes, "审批成功!");
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripTaskList?repage";
	}
	
	/**
	 * 进入出差审批页面--财务
	 * @author Meng
	 */
	@RequestMapping(value = "toApproveBusinessTripInfo_FA")
	public String toApproveBusinessTripInfo_FA(String id, Model model) {
		BusinessTripHotelHelper businessTripHotelHelper = new BusinessTripHotelHelper();
		BusinessTripApplication businessTripApplication = new BusinessTripApplication();
		businessTripApplication.setId(id);
		String name = businessTripService.getBusinessTripApplicationInfo(id).getApplicant().getName();
		businessTripApplication.setFAComment("同意"+name+"的出差申请。");
		businessTripHotelHelper.setBusinessTripApplication(businessTripApplication);
		model.addAttribute("businessTripHotelHelper", businessTripHotelHelper);
		return "modules/fa/businessTripFAApprove";
	}
	
	/**
	 * 出差审批--财务
	 * @author Meng
	 */
	@RequestMapping(value = "approveBusinessTripInfo_FA")
	public String approveBusinessTripInfo_FA(BusinessTripHotelHelper businessTripHotelHelper, RedirectAttributes redirectAttributes) {
		businessTripService.FAApprove(businessTripHotelHelper);
		businessTripService.completeFAProcess(businessTripHotelHelper.getBusinessTripApplication().getId());
		String name = businessTripService.getBusinessTripApplicationInfo(businessTripHotelHelper.getBusinessTripApplication().getId()).getApplicant().getName();
		addMessage(redirectAttributes, "审批成功！ "+name+"的出差申请已通过！");
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripTaskList?repage";
	}
	
	
	/**
	 * 查看出差详细信息
	 * @author Meng
	 */
	@RequestMapping(value = "toShowBusinessTripInfo")
	public String toShowBusinessTripInfo(String id, String mode, Model model) {
		BusinessTripApplication businessTripApplication = businessTripService.getBusinessTripApplicationInfo(id);
		List<BusinessTripReservation> businessTripReservationList = businessTripService.getBusinessTripReservationList(id);
		List<BusinessTripAirTicket> businessTripAirTicketList = businessTripService.getBusinessTripAirTicketList(id);
		BusinessTripHotel businessTripHotel = businessTripService.getBusinessTripHotel(id);
		model.addAttribute("businessTripApplication", businessTripApplication);
		model.addAttribute("businessTripReservationList", businessTripReservationList);
		model.addAttribute("businessTripAirTicketList", businessTripAirTicketList);
		model.addAttribute("businessTripHotel", businessTripHotel);
		model.addAttribute("mode", mode);
		return "modules/fa/businessTripInfoShow";
	}
	
	/**
	 * 出差信息查询
	 * @author Meng
	 */
	@RequestMapping(value = "toBusinessTripInfoList")
	public String businessTripInfoList(BusinessTripApplication BusinessTripApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
		String projectName = null;
		if(BusinessTripApplication.getProject() != null) {
			projectName = BusinessTripApplication.getProject().getName();
		}
		List<BusinessTripApplication> list = businessTripService.businessTripInfoList(UserUtils.getUser(), projectName);
		Page<BusinessTripApplication> page = businessTripService.findPage(new Page<BusinessTripApplication>(request, response), list);
		model.addAttribute("page", page);
		List<String> projectNameList = businessTripService.getBusinessTripProjectNameList();
		model.addAttribute("projectNameList", projectNameList);
		return "modules/fa/businessTripInfoList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
