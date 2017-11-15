package com.thinkgem.jeesite.modules.oa.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.oa.entity.TestAudit;
import com.thinkgem.jeesite.modules.oa.service.AttendanceApprovalService;
import com.thinkgem.jeesite.modules.oa.service.AttendanceMonthService;

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

	@RequestMapping(value = "saveAttendanceApproval")
	public String saveAttendanceApproval(AttendanceMonth attendanceMonth, Model model) {
		if (StringUtils.isBlank(attendanceMonth.getAct().getFlag())
				|| StringUtils.isBlank(attendanceMonth.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
			// return form(attendanceMonth, model);
		}
		attendanceApprovalService.saveAttendanceApproval(attendanceMonth);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequestMapping(value = "form")
	public String form(AttendanceMonth attendanceMonth, Model model) {

		String view = "testAuditForm";

		// 查看审批申请单
		if (StringUtils.isNotBlank(attendanceMonth.getId())) {// .getAct().getProcInsId())){

			// 环节编号
			String taskDefKey = attendanceMonth.getAct().getTaskDefKey();

			// 查看工单
			if (attendanceMonth.getAct().isFinishTask()) {
				view = "testAuditView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)) {
				view = "testAuditForm";
			}
			// 审核环节
			else if ("audit".equals(taskDefKey)) {
				view = "testAuditAudit";
				// String formKey = "/oa/testAudit";
				// return "redirect:" + ActUtils.getFormUrl(formKey,
				// testAudit.getAct());
			}
			// 审核环节2
			else if ("audit2".equals(taskDefKey)) {
				view = "testAuditAudit";
			}
			// 审核环节3
			else if ("audit3".equals(taskDefKey)) {
				view = "testAuditAudit";
			}
			// 审核环节4
			else if ("audit4".equals(taskDefKey)) {
				view = "testAuditAudit";
			}
			// 兑现环节
			else if ("apply_end".equals(taskDefKey)) {
				view = "testAuditAudit";
			}
		}
		model.addAttribute("attendanceMonth", attendanceMonth);
		return "modules/oa/" + view;
	}
	
	
	
	
	
}
