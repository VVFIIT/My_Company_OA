package com.thinkgem.jeesite.modules.finance.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.finance.entity.ReimburseHospitality;
import com.thinkgem.jeesite.modules.finance.service.ReimburseHospitalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.helper.ReimburseModel;
import com.thinkgem.jeesite.modules.finance.service.ReimburseService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import java.util.List;

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

	
	@RequestMapping(value = "commitApplyForm")
	public String commitApplyForm(ReimburseModel reimburseModel, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
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
