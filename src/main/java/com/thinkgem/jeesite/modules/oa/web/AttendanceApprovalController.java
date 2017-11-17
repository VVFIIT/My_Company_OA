package com.thinkgem.jeesite.modules.oa.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.oa.service.AttendanceApprovalService;

/**
 * 考勤审批
 * 
 * @author Grace
 * @date 2017年11月13日 下午3:06:36
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/attendanceApproval")
public class AttendanceApprovalController extends BaseController {

	@Autowired
	private AttendanceApprovalService attendanceApprovalService;

	@RequestMapping(value = "save")
	public String saveAttendanceApproval(AttendanceMonth attendanceMonth, Model model,HttpServletRequest request) {
	 
		if (StringUtils.isBlank(attendanceMonth.getAct().getFlag())
				|| StringUtils.isBlank(attendanceMonth.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
		}		

		//环节KEY
		String taskDefKey=request.getParameter("taskDefKey").toString();
		String taskId=request.getParameter("taskId").toString();
		attendanceMonth.getAct().setTaskDefKey(taskDefKey);
		attendanceMonth.getAct().setTaskId(taskId);
		attendanceApprovalService.saveAttendanceApproval(attendanceMonth);
		return "redirect:" + adminPath + "/act/task/todo/"; 
	}

	
	
	
	
	
	
}
