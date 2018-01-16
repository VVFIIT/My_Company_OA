package com.thinkgem.jeesite.modules.oa.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String saveAttendanceApproval(AttendanceMonth attendanceMonth, RedirectAttributes redirectAttributes) {
	 
//		if (StringUtils.isBlank(attendanceMonth.getAct().getFlag())
//				|| StringUtils.isBlank(attendanceMonth.getAct().getComment())) {
//			addMessage(model, "请填写审核意见。");
//		}		

		//环节KEY
//		String taskDefKey=request.getParameter("taskDefKey").toString();
//		String taskId=request.getParameter("taskId").toString();
//		attendanceMonth.getAct().setTaskDefKey(taskDefKey);
//		attendanceMonth.getAct().setTaskId(taskId);
		attendanceApprovalService.saveAttendanceApproval(attendanceMonth);
		addMessage(redirectAttributes, "审批成功!");
		return "redirect:" + adminPath + "/oa/attendance/toDo?repage"; 
	}

	
	
	
	
	
	
}
