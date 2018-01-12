package com.thinkgem.jeesite.modules.finance.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.Project;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseHospitality;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseLongDistance;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseOther;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseTaxi;
import com.thinkgem.jeesite.modules.finance.service.ReimburseService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 报销
 *
 * @author Grace
 * @date 2017年12月25日 下午4:05:32
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "${adminPath}/fa/reimburse")
public class ReimburseController extends BaseController {

	@Autowired
	private ReimburseService reimburseService;

	/**
	 * 报销申请页
	 *
	 * @param reimburseMain
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @author Grace
	 * @date 2018年1月3日 下午4:59:54
	 */
	@RequestMapping(value = "toApplyForm")
	public String toApplyForm(ReimburseMain reimburseMain, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		User user = UserUtils.getUser();
		List<Project> projectList = reimburseService.getProjectList();
		model.addAttribute("projectList", projectList);
		model.addAttribute("applicantName", user.getName());
		model.addAttribute("officeName", user.getOffice().getName());
		model.addAttribute("reimburseMain", reimburseMain);
		model.addAttribute("applyDate", new Date());
		return "modules/fa/reimburse/reimburseApplyForm";
	}

	/**
	 * 提交报销申请
	 *
	 * @param reimburseModel
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @author Grace
	 * @throws ParseException
	 * @date 2018年1月3日 下午5:00:03
	 */
	@RequestMapping(value = "commitApplyForm")
	public String commitApplyForm(ReimburseMain reimburseMain, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws ParseException {

		try {
			String mainId = UUID.randomUUID().toString();
			reimburseService.insertReimburse(reimburseMain, request, mainId);
			addMessage(redirectAttributes, "报销申请成功!");
		} catch (ParseException e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "报销申请失败!");
		}
		return "redirect:" + Global.getAdminPath() + "/fa/reimburse/list?repage";
	}

	/**
	 * 费用报销查看列表
	 * 
	 * @param reimburseModel
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @author Grace
	 * @date 2018年1月8日 上午11:03:30
	 */
	@RequestMapping(value = "list")
	public String list(ReimburseMain reimburseMain, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Page<ReimburseMain> page = reimburseService.reimburseMainList(new Page<ReimburseMain>(request, response),
				reimburseMain);
		model.addAttribute("page", page);
		model.addAttribute("reimburseMain", reimburseMain);
		return "modules/fa/reimburse/reimburseList";
	}

	/**
	 * 报销费用综合申报表查看
	 * 
	 * @param reimburseMain
	 * @param model
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @author Grace
	 * @date 2018年1月8日 下午12:02:53
	 */
	@RequestMapping(value = "show")
	public String show(Model model, HttpServletRequest request, HttpServletResponse response, String mainId) {
		ReimburseMain reimburseMain = reimburseService.getMainById(mainId);
		List<ReimburseLongDistance> longDistanceList = reimburseService.getLongDistanceListByMainId(mainId);
		List<ReimburseTaxi> taxiList = reimburseService.getTaxiListByMainId(mainId);
		List<ReimburseHospitality> hospitalityList = reimburseService.getHospitalityListByMainId(mainId);
		List<ReimburseOther> otherList = reimburseService.getOtherListByMainId(mainId);

		model.addAttribute("reimburseMain", reimburseMain);
		model.addAttribute("taxiList", taxiList);
		model.addAttribute("hospitalityList", hospitalityList);
		model.addAttribute("otherList", otherList);
		model.addAttribute("longDistanceList", longDistanceList);
		return "modules/fa/reimburse/reimburseShow";
	}

	/**
	 * 我的任务列表
	 * 
	 * @param reimburseModel
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @author Grace
	 * @date 2018年1月8日 下午5:30:18
	 */
	@RequestMapping(value = "taskList")
	public String taskList(ReimburseMain reimburseMain, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		User user = UserUtils.getUser();
		String enname = null;
		List<Role> roleList = user.getRoleList();
		if (roleList != null && roleList.size() > 0) {
			enname = roleList.get(0).getEnname();
		}

		Page<ReimburseMain> page = reimburseService.reimburseTaskListList(new Page<ReimburseMain>(request, response),
				reimburseMain);
		model.addAttribute("page", page);
		model.addAttribute("reimburseMain", reimburseMain);
		model.addAttribute("role", enname);
		return "modules/fa/reimburse/reimburseTaskList";
	}

	/**
	 * 审批
	 * 
	 * @param reimburseModel
	 * @param model
	 * @return
	 * @author Grace
	 * @date 2018年1月8日 下午5:50:57
	 */
	@RequestMapping(value = "approve")
	public String approve(ReimburseMain reimburseMain, Model model) {
		model.addAttribute("reimburseMain", reimburseMain);
		return "modules/fa/reimburse/reimburseApprove";
	}

	/**
	 * 保存审批
	 * 
	 * @param reimburseModel
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @author Grace
	 * @date 2018年1月8日 下午6:03:03
	 */
	@RequestMapping(value = "approveSave")
	public String approveSave(ReimburseMain reimburseMain, HttpServletRequest request,
			RedirectAttributes redirectAttributes, Model model) {

		if (StringUtils.isBlank(reimburseMain.getAct().getFlag())
				|| StringUtils.isBlank(reimburseMain.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
		}
		reimburseService.saveApprove(reimburseMain);
		addMessage(redirectAttributes, "审批成功！");
		return "redirect:" + Global.getAdminPath() + "/fa/reimburse/taskList?repage";

	}

	/**
	 * 打开修改页面
	 * 
	 * @param reimburseMain
	 * @param model
	 * @return
	 * @author Grace
	 * @date 2018年1月11日 下午2:49:17
	 */
	@RequestMapping(value = "update")
	public String toUpdate(Model model, String mainId) {

		ReimburseMain reimburseMain = reimburseService.getMainById(mainId);
		List<ReimburseLongDistance> longDistanceList = reimburseService.getLongDistanceListByMainId(mainId);
		List<ReimburseTaxi> taxiList = reimburseService.getTaxiListByMainId(mainId);
		List<ReimburseHospitality> hospitalityList = reimburseService.getHospitalityListByMainId(mainId);
		List<ReimburseOther> otherList = reimburseService.getOtherListByMainId(mainId);

		User user = UserUtils.getUser();

		// 拼接
		if (longDistanceList != null && longDistanceList.size() > 0) {
			model.addAttribute("longDistanceEveryNum", appendEveryNum(longDistanceList.size()));
		} else {
			model.addAttribute("longDistanceEveryNum", "1");
		}

		if (taxiList != null && taxiList.size() > 0) {
			model.addAttribute("taxiEveryNum", appendEveryNum(taxiList.size()));
		} else {
			model.addAttribute("taxiEveryNum", "1");
		}

		if (hospitalityList != null && hospitalityList.size() > 0) {
			model.addAttribute("hospitalityEveryNum", appendEveryNum(hospitalityList.size()));
		} else {
			model.addAttribute("hospitalityEveryNum", "1");
		}

		if (otherList != null && otherList.size() > 0) {
			model.addAttribute("otherEveryNum", appendEveryNum(otherList.size()));
		} else {
			model.addAttribute("otherEveryNum", "1");
		}

		List<Project> projectList = reimburseService.getProjectList();

		model.addAttribute("applicantName", user.getName());
		model.addAttribute("officeName", user.getOffice().getName());
		model.addAttribute("projectList", projectList);

		model.addAttribute("reimburseMain", reimburseMain);
		model.addAttribute("taxiList", taxiList);
		model.addAttribute("hospitalityList", hospitalityList);
		model.addAttribute("otherList", otherList);
		model.addAttribute("longDistanceList", longDistanceList);

		return "modules/fa/reimburse/reimburseUpdate";
	}

	/**
	 * 拼接
	 * 
	 * @param list
	 * @return
	 * @author Grace
	 * @date 2018年1月11日 下午6:13:34
	 */
	public String appendEveryNum(Integer size) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(i + 1);
		}
		String everyNum = sb.toString();
		return everyNum;

	}

	/**
	 * 保存修改
	 * 
	 * @param reimburseMain
	 * @param request
	 * @param redirectAttributes
	 * @param model
	 * @return
	 * @author Grace
	 * @throws ParseException
	 * @date 2018年1月11日 下午2:51:11
	 */
	@RequestMapping(value = "updateSave")
	public String updateSave(ReimburseMain reimburseMain, HttpServletRequest request,
			RedirectAttributes redirectAttributes, Model model) throws ParseException {

		reimburseService.saveUpdate(reimburseMain, request);
		addMessage(redirectAttributes, "修改成功！");
		return "redirect:" + Global.getAdminPath() + "/fa/reimburse/taskList?repage";

	}

	/**
	 * 删除
	 * 
	 * @param reimburseMain
	 * @param request
	 * @param redirectAttributes
	 * @param model
	 * @return
	 * @throws ParseException
	 * @author Grace
	 * @date 2018年1月12日 上午11:10:00
	 */
	@RequestMapping(value = "delete")
	public String delete(String mainId, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model)
			throws ParseException {

		reimburseService.deleteReimburse(mainId);
		addMessage(redirectAttributes, "删除成功！");
		return "redirect:" + Global.getAdminPath() + "/fa/reimburse/taskList?repage";

	}
}
