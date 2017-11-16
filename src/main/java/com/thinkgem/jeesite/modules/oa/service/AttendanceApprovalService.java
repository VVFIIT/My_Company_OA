package com.thinkgem.jeesite.modules.oa.service;

import java.util.Map;

import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.dao.AttendanceMonthDao;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;

/**
 * 考勤审批
 * 
 * @author Grace
 * @date 2017年11月13日 下午3:07:42
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AttendanceApprovalService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AttendanceMonthDao attendanceMonthDao;

	@Autowired
	private TaskService taskService;
	
	/**
	 * 保存考勤审批
	 * 
	 * @param attendanceMonth
	 * @author Grace
	 * @date 2017年11月13日 下午3:16:35
	 */
	public void saveAttendanceApproval(AttendanceMonth attendanceMonth) {

		// 设置意见
		attendanceMonth.getAct().setComment(("yes".equals(attendanceMonth.getAct().getFlag()) ? "[同意] " : "[驳回] ")
				+ attendanceMonth.getAct().getComment());

		
		//attendanceMonth.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = attendanceMonth.getAct().getTaskDefKey();

		// 审核环节
		if ("startAttendance".equals(taskDefKey)) {

		} else if ("PMApprovalAttendance".equals(taskDefKey)) {
			attendanceMonth.setProcessStatus("3");
			attendanceMonth.setPMComment(attendanceMonth.getAct().getComment());
			attendanceMonthDao.updateAttanceMonthByProcInsId(attendanceMonth);
		} else if ("HRApprovalAttendance".equals(taskDefKey)) {
			attendanceMonth.setProcessStatus("4");
			attendanceMonth.setHRComment(attendanceMonth.getAct().getComment());
			attendanceMonthDao.updateAttanceMonthByProcInsId(attendanceMonth);
		} else if ("endAttendance".equals(taskDefKey)) {

		}

		// 未知环节，直接返回
		else {
			return;
		}

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(attendanceMonth.getAct().getFlag()) ? "1" : "0");	
		
		complete(attendanceMonth.getAct().getTaskId(), attendanceMonth.getAct().getProcInsId(),
				attendanceMonth.getAct().getComment(),taskDefKey, vars);

	}
	
	
	public void complete(String taskId, String procInsId, String comment, String title, Map<String, Object> vars) {
		// 添加意见
		if (StringUtils.isNotBlank(procInsId) && StringUtils.isNotBlank(comment)) {
			//封装
			taskService.addComment(taskId, procInsId, comment);
		}

		// 设置流程变量
		if (vars == null) {
			vars = Maps.newHashMap();
		}

		// 设置流程标题
		if (StringUtils.isNotBlank(title)) {
			vars.put("title", title);
		}

		// 提交任务（封装）
		taskService.complete(taskId, vars);
	}

}
