package com.thinkgem.jeesite.modules.finance.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseHospitality;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.helper.ReimburseModel;
import com.thinkgem.jeesite.modules.finance.service.ReimburseHospitalityService;
import com.thinkgem.jeesite.modules.finance.service.ReimburseService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

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

	@Autowired
	private ReimburseHospitalityService reimburseHospitalityService;

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
		ReimburseModel reimburseModel = new ReimburseModel();
		User user = UserUtils.getUser();
		reimburseModel.setUserName(user.getName());
		reimburseModel.setOfficeName(user.getOffice().getName());

		model.addAttribute("reimburseModel", reimburseModel);
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
	public String commitApplyForm(ReimburseModel reimburseModel, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws ParseException {
		try {
			String mainId = UUID.randomUUID().toString();
			reimburseService.insertReimburse(reimburseModel, request, mainId);
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
	public String list(ReimburseModel reimburseModel, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Page<ReimburseModel> page = reimburseService.reimburseMainList(new Page<ReimburseModel>(request, response),
				reimburseModel);
		model.addAttribute("page", page);
		model.addAttribute("reimburseModel", reimburseModel);
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
	public String show(Model model, HttpServletRequest request, HttpServletResponse response, String id) {
		ReimburseModel reimburseModel = reimburseService.reimburseShow(id);
		model.addAttribute("reimburseModel", reimburseModel);
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
	public String taskList(ReimburseModel reimburseModel, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Page<ReimburseModel> page = reimburseService.reimburseTaskListList(new Page<ReimburseModel>(request, response),
				reimburseModel);
		model.addAttribute("page", page);
		model.addAttribute("reimburseModel", reimburseModel);
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
	public String approve(ReimburseModel reimburseModel, Model model) {
		model.addAttribute("reimburseModel", reimburseModel);
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
	public String approveSave(ReimburseModel reimburseModel, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String flag = request.getParameter("flag");
		reimburseService.saveApprove(reimburseModel, flag);
		addMessage(redirectAttributes, "审批信息保存成功");
		return "redirect:" + Global.getAdminPath() + "/fa/reimburse/?repage";
	}
}
