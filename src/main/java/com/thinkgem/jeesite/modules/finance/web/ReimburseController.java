package com.thinkgem.jeesite.modules.finance.web;
import java.text.ParseException;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.helper.ReimburseModel;
import com.thinkgem.jeesite.modules.finance.service.ReimburseService;
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
		ReimburseModel reimburseModel=new ReimburseModel();
		User user=UserUtils.getUser();
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
	 * @date 2018年1月3日 下午5:00:03
	 */
	@RequestMapping(value = "commitApplyForm")
	public String commitApplyForm(ReimburseModel reimburseModel, HttpServletRequest request,
			 RedirectAttributes redirectAttributes) {
		String mainId = UUID.randomUUID().toString();
		try {
			reimburseService.insertReimburse(request,mainId);
			addMessage(redirectAttributes, "出差申请成功!");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "出差申请失败!");
		}
		return "redirect:" + Global.getAdminPath() + "/fa/reimburse/toApplyForm?repage";
	}


	/**
	 * 费用报销显示列表
	 */
	@RequestMapping(value = "list")
	public String list(ReimburseMain reimburseMain, Model model, HttpServletRequest request,
					   HttpServletResponse response) {
		Page<ReimburseMain> page = reimburseService.reimburseMainList(new Page<ReimburseMain>(request, response),reimburseMain);
		model.addAttribute("page", page);
		User user = UserUtils.getUser();
		reimburseMain.setApplicantId(user.getId());
		model.addAttribute("reimburseMainName", reimburseMain);
		return "modules/fa/reimburseList";
	}


	/**
	 * 报销费用综合申报表查看
	 */
	@RequestMapping(value = "formList")
	public String formList(ReimburseMain reimburseMain, Model model, HttpServletRequest request,
					   HttpServletResponse response) {
//		Page<ReimburseMain> page = reimburseService.reimburseMainList(new Page<ReimburseMain>(request, response),reimburseMain);
//		model.addAttribute("page", page);
//		User user = UserUtils.getUser();
//		reimburseMain.setApplicantId(user.getId());
//		model.addAttribute("reimburseMainName", reimburseMain);
		return "modules/fa/reimburseFormList";
	}
}
