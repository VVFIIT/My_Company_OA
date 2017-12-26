package com.thinkgem.jeesite.modules.finance.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.finance.service.ReimburseService;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;

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

	
	@RequestMapping(value = "toApplyForm")
	public String toApplyForm(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		return "modules/fa/reimburse/reimburseApplyForm";
	}

	
	@RequestMapping(value = "commitApplyForm")
	public String commitApplyForm(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		return "modules/fa/reimburse/reimburseApplyForm";
	}
	
	
	
}
