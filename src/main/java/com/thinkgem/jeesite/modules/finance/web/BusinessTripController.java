/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.web;

import java.text.ParseException;
import java.util.ArrayList;
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
			addMessage(redirectAttributes, "出差申请成功！已提交到经理处审批！可在出差查询中查看申请进度！");
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
	 * @author Meng
	 */
	@RequestMapping(value = "toupdateBusinessTripInfo")
	public String toupdateBusinessTripInfo(String id, Model model) {
		BusinessTripApplication businessTripApplication = businessTripService.getBusinessTripApplicationInfo(id);
		List<BusinessTripReservation> businessTripReservationList = businessTripService.getBusinessTripReservationList(id);
		List<BusinessTripAirTicket> businessTripAirTicketList = businessTripService.getBusinessTripAirTicketList(id);
		model.addAttribute("businessTripApplicationId", id);
		model.addAttribute("businessTripApplication", businessTripApplication);
		if (businessTripReservationList!=null) {
			model.addAttribute("reservationEveryNum", businessTripReservationList.size());
		}
		if (businessTripAirTicketList!=null) {
			model.addAttribute("airTicketEveryNum", businessTripAirTicketList.size());
		}
		model.addAttribute("businessTripReservationList", businessTripReservationList);
		model.addAttribute("businessTripAirTicketList", businessTripAirTicketList);
		List<String> projectNameList = businessTripService.getBusinessTripProjectNameList();
		model.addAttribute("projectNameList", projectNameList);
		return "modules/fa/businessTripUpdateForm";
	}
	
	/**
	 * 提交更新出差信息
	 * @author Meng
	 */
	@RequestMapping(value = "updateBusinessTripInfo")
	public String updateBusinessTripInfo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String businessTripApplicationId = request.getParameter("businessTripApplicationId");
		try {
			businessTripService.updateBusinessTripApplication(request, businessTripApplicationId);
			businessTripService.updateBusinessTripReservation(request, businessTripApplicationId);
			businessTripService.updateBusinessTripAirTicket(request, businessTripApplicationId);
			String procInsId = businessTripService.findProcInsIdByApplicationId(businessTripApplicationId);
			businessTripService.completeApplicantProcess(procInsId, businessTripApplicationId);
			addMessage(redirectAttributes, "出差申请修改成功！已再次提交申请！可在出差查询中查看申请进度！");
		} catch (ParseException e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "出差申请修改失败！");
		}
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
		String insertFlag = businessTripService.getBusinessTripApplicationInfo(id).getInsertFlag();
		if ("yes".equals(insertFlag)) {
			businessTripApplication.setManagerComment("同意"+name+"的出差追加申请。");
		} else {
			businessTripApplication.setManagerComment("同意"+name+"的出差申请。");
		}
		
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
		businessTripService.completeManagerProcess(businessTripApplication.getManagerFlag(), businessTripApplication.getId());
		BusinessTripApplication application = businessTripService.getBusinessTripApplicationInfo(businessTripApplication.getId());
		if ("yes".equals(application.getInsertFlag())) {
			if("yes".equals(application.getManagerFlag())) {
				addMessage(redirectAttributes, "审批完成！您同意了"+application.getApplicant().getName()+"的出差追加申请");
			}else {
				addMessage(redirectAttributes, "审批完成！您驳回了"+application.getApplicant().getName()+"的出差追加申请");
			}
		} else {
			if("yes".equals(application.getManagerFlag())) {
				addMessage(redirectAttributes, "审批完成！您同意了"+application.getApplicant().getName()+"的出差申请");
			}else {
				addMessage(redirectAttributes, "审批完成！您驳回了"+application.getApplicant().getName()+"的出差申请");
			}
		}
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripTaskList?repage";
	}
	
	/**
	 * 进入出差审批页面--财务
	 * @author Meng
	 */
	@RequestMapping(value = "toApproveBusinessTripInfo_FA")
	public String toApproveBusinessTripInfo_FA(String id, Model model) {
		BusinessTripHotelHelper businessTripHotelHelper = new BusinessTripHotelHelper();
		BusinessTripApplication businessTripApplication = businessTripService.getBusinessTripApplicationInfo(id);
		String name = businessTripApplication.getApplicant().getName();
		String insertFlag = businessTripApplication.getInsertFlag();
		if ("yes".equals(insertFlag)) {
			businessTripApplication.setFAComment("同意"+name+"的出差追加申请。");
		} else {
			businessTripApplication.setFAComment("同意"+name+"的出差申请。");
		}
		businessTripHotelHelper.setBusinessTripApplication(businessTripApplication);
		List<BusinessTripReservation> businessTripReservationList = businessTripService.getBusinessTripReservationList(id);
		List<BusinessTripReservation> businessTripReservationInsertList = businessTripService.getBusinessTripReservationInsertList(id);
		model.addAttribute("businessTripReservationList", businessTripReservationList);
		model.addAttribute("businessTripHotelHelper", businessTripHotelHelper);
		if ("yes".equals(businessTripApplication.getInsertFlag())) {
			if(businessTripReservationInsertList.size()==0) {
				return "modules/fa/businessTripFAApprove_NoHotel";
			} else {
				model.addAttribute("businessTripReservationInsertList", businessTripReservationInsertList);
				return "modules/fa/businessTripFAApprove_Hotel";
			}
		} else {
			if(businessTripReservationList.size()==0) {
				return "modules/fa/businessTripFAApprove_NoHotel";
			} else {
				model.addAttribute("businessTripReservationList", businessTripReservationList);
				return "modules/fa/businessTripFAApprove_Hotel";
			}
		}
		
		
	}
	
	/**
	 * 出差审批不添加酒店--财务
	 * @author Meng
	 */
	@RequestMapping(value = "approveBusinessTripInfo_FA")
	public String approveBusinessTripInfo_FA(BusinessTripHotelHelper businessTripHotelHelper, RedirectAttributes redirectAttributes) {
		businessTripService.FAApprove(businessTripHotelHelper);
		businessTripService.completeFAProcess(businessTripHotelHelper.getBusinessTripApplication().getFAFlag(), businessTripHotelHelper.getBusinessTripApplication().getId());
		String name = businessTripService.getBusinessTripApplicationInfo(businessTripHotelHelper.getBusinessTripApplication().getId()).getApplicant().getName();
		addMessage(redirectAttributes, "审批完成！ 您驳回了"+name+"的出差申请！");
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripTaskList?repage";
	}
	
	/**
	 * 出差审批拒绝--财务
	 * @author Meng
	 */
	@RequestMapping(value = "approveBusinessTripInfo_FA_Reject")
	public String approveBusinessTripInfo_FA_Reject(BusinessTripHotelHelper businessTripHotelHelper, RedirectAttributes redirectAttributes) {
		businessTripService.FAApprove(businessTripHotelHelper);
		businessTripService.completeFAProcess(businessTripHotelHelper.getBusinessTripApplication().getFAFlag(), businessTripHotelHelper.getBusinessTripApplication().getId());
		String name = businessTripService.getBusinessTripApplicationInfo(businessTripHotelHelper.getBusinessTripApplication().getId()).getApplicant().getName();
		String insertFlag = businessTripService.getBusinessTripApplicationInfo(businessTripHotelHelper.getBusinessTripApplication().getId()).getInsertFlag();
		if ("yes".equals(insertFlag)) {
			addMessage(redirectAttributes, "审批完成！ 您驳回了"+name+"的出差追加申请！");
		} else {
			addMessage(redirectAttributes, "审批完成！ 您驳回了"+name+"的出差申请！");
		}
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripTaskList?repage";
	}
	
	/**
	 * 出差审批同意--财务
	 * @author Meng
	 */
	@RequestMapping(value = "approveBusinessTripInfo_FA_Agree")
	public String approveBusinessTripInfo_FA_Agree(BusinessTripHotelHelper businessTripHotelHelper, RedirectAttributes redirectAttributes) {
		businessTripService.FAApprove(businessTripHotelHelper);
		businessTripService.completeFAProcess(businessTripHotelHelper.getBusinessTripApplication().getFAFlag(), businessTripHotelHelper.getBusinessTripApplication().getId());
		String name = businessTripService.getBusinessTripApplicationInfo(businessTripHotelHelper.getBusinessTripApplication().getId()).getApplicant().getName();
		String insertFlag = businessTripService.getBusinessTripApplicationInfo(businessTripHotelHelper.getBusinessTripApplication().getId()).getInsertFlag();
		if ("yes".equals(insertFlag)) {
			addMessage(redirectAttributes, "审批完成！ 您同意了"+name+"的出差追加申请！");
		} else {
			addMessage(redirectAttributes, "审批完成！ 您同意了"+name+"的出差申请！");
		}
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
		List<BusinessTripHotelHelper> businessTripHotelHelperList = new ArrayList<BusinessTripHotelHelper>();
		for (BusinessTripReservation businessTripReservation : businessTripReservationList) {
			BusinessTripHotelHelper businessTripHotelHelper = new BusinessTripHotelHelper();
			BusinessTripHotel businessTripHotel = businessTripService.getBusinessTripHotel(businessTripReservation.getId());
			businessTripHotelHelper.setBusinessTripReservation(businessTripReservation);
			businessTripHotelHelper.setBusinessTripHotel(businessTripHotel);
			businessTripHotelHelperList.add(businessTripHotelHelper);
		}
		model.addAttribute("businessTripHotelHelperList", businessTripHotelHelperList);
		model.addAttribute("businessTripApplication", businessTripApplication);
		model.addAttribute("businessTripReservationList", businessTripReservationList);
		model.addAttribute("businessTripAirTicketList", businessTripAirTicketList);
		model.addAttribute("reservationListSize", businessTripReservationList.size());
		model.addAttribute("airTicketListSize", businessTripAirTicketList.size());
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
		model.addAttribute("loginUserId", UserUtils.getUser().getId());
		return "modules/fa/businessTripInfoList";
	}
	
	/**
	 * 进入出差信息追加页面
	 * @author Meng
	 */
	@RequestMapping(value = "toInsertBusinessTripInfo")
	public String toInsertBusinessTripInfo(String id, Model model) {
		BusinessTripApplication businessTripApplication = businessTripService.getBusinessTripApplicationInfo(id);
		List<BusinessTripReservation> businessTripReservationList = businessTripService.getBusinessTripReservationList(id);
		List<BusinessTripAirTicket> businessTripAirTicketList = businessTripService.getBusinessTripAirTicketList(id);
		model.addAttribute("businessTripApplicationId", id);
		model.addAttribute("businessTripApplication", businessTripApplication);
		model.addAttribute("businessTripReservationList", businessTripReservationList);
		model.addAttribute("businessTripAirTicketList", businessTripAirTicketList);
		return "modules/fa/businessTripInsertForm";
	}
	
	/**
	 * 追加出差申请页面提交
	 * @author Meng
	 */
	@RequestMapping(value = "insertBusinessTripInfo")
	public String insertBusinessTripInfo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String applicationId = request.getParameter("businessTripApplicationId");
		try {
			businessTripService.resetComment(request, applicationId);
			businessTripService.resetInsertFlag(applicationId);
			businessTripService.insertBusinessTripReservation(request, applicationId);
			businessTripService.insertBusinessTripAirTicket(request, applicationId);
			String procInsId = businessTripService.startBusinessTripProcess(applicationId);
			businessTripService.completeApplicantProcess(procInsId, applicationId);
			addMessage(redirectAttributes, "出差申请追加成功！已提交到经理处审批！可在出差查询中查看申请进度！");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripInfoList?repage";
	}
	
	/**
	 * 进入出差追加信息更新页面
	 * @author Meng
	 */
	@RequestMapping(value = "toupdateBusinessTripInsertInfo")
	public String toupdateBusinessTripInsertInfo(String id, Model model) {
		BusinessTripApplication businessTripApplication = businessTripService.getBusinessTripApplicationInfo(id);
		List<BusinessTripReservation> businessTripReservationNoInsertList = businessTripService.getBusinessTripReservationNoInsertList(id);
		List<BusinessTripAirTicket> businessTripAirTicketNoInsertList = businessTripService.getBusinessTripAirTicketNoInsertList(id);
		List<BusinessTripReservation> businessTripReservationInsertList = businessTripService.getBusinessTripReservationInsertList(id);
		List<BusinessTripAirTicket> businessTripAirTicketInsertList = businessTripService.getBusinessTripAirTicketInsertList(id);
		model.addAttribute("businessTripApplicationId", id);
		model.addAttribute("businessTripApplication", businessTripApplication);
		if(businessTripReservationInsertList!=null) {
			model.addAttribute("reservationEveryNum", businessTripReservationInsertList.size());
		}
		if (businessTripAirTicketInsertList!=null) {
			model.addAttribute("airTicketEveryNum", businessTripAirTicketInsertList.size());
		}
		model.addAttribute("businessTripReservationList", businessTripReservationNoInsertList);
		model.addAttribute("businessTripAirTicketList", businessTripAirTicketNoInsertList);
		model.addAttribute("businessTripReservationInsertList", businessTripReservationInsertList);
		model.addAttribute("businessTripAirTicketInsertList", businessTripAirTicketInsertList);
		return "modules/fa/businessTripUpdateInsertForm";
	}
	
	/**
	 * 提交更新出差追加信息
	 * @author Meng
	 */
	@RequestMapping(value = "updateBusinessTripInsertInfo")
	public String updateBusinessTripInsertInfo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String businessTripApplicationId = request.getParameter("businessTripApplicationId");
		try {
			businessTripService.updateBusinessTripInsertApplication(request, businessTripApplicationId);
			businessTripService.updateBusinessTripReservation(request, businessTripApplicationId);
			businessTripService.updateBusinessTripAirTicket(request, businessTripApplicationId);
			String procInsId = businessTripService.findProcInsIdByApplicationId(businessTripApplicationId);
			businessTripService.completeApplicantProcess(procInsId, businessTripApplicationId);
			String insertFlag = businessTripService.getBusinessTripApplicationInfo(businessTripApplicationId).getInsertFlag();
			if ("yes".equals(insertFlag)) {
				addMessage(redirectAttributes, "出差追加申请修改成功！已再次提交申请！可在出差查询中查看申请进度！");
			} else {
				addMessage(redirectAttributes, "出差申请修改成功！已再次提交申请！可在出差查询中查看申请进度！");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "出差申请修改失败！");
		}
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripTaskList?repage";
	}
	
	/**
	 * 删除出差信息
	 * @author Meng
	 */
	@RequestMapping(value = "deleteBusinessTripInfo")
	public String deleteBusinessTripInfo(String id, RedirectAttributes redirectAttributes) {
		businessTripService.deleteBusinessTripReservation(id);
		businessTripService.deleteBusinessTripAirTicket(id);
		businessTripService.deleteBusinessTripApplication(id);
		return "redirect:" + Global.getAdminPath() + "/fa/businessTrip/toBusinessTripTaskList?repage";
	}
	
	
	
	
}
