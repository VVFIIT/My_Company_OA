package com.thinkgem.jeesite.modules.finance.web;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String commitApplyForm(ReimburseModel reimburseModel,  HttpServletRequest request,
								  HttpServletResponse response) {
		String mainId = UUID.randomUUID().toString();
		reimburseService.insertReimburse(reimburseModel,request,mainId);
		return "modules/fa/reimburse/reimburseApplyForm";
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
						   HttpServletResponse response, String id) {
		ReimburseMain reimburseMains = reimburseService.reimburseInformation(id);
		model.addAttribute("reimburseMain", reimburseMains);
		List<ReimburseHospitality> reimburseHospitalityList=reimburseHospitalityService.reimburseHospitalityInformation(id);
		System.out.println("_________________________________"+reimburseHospitalityList);
		return "modules/fa/reimburseFormList";
	}
}
