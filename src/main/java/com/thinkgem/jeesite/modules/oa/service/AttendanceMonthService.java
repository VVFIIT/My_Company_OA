package com.thinkgem.jeesite.modules.oa.service;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.utils.ProcessDefCache;
import com.thinkgem.jeesite.modules.oa.dao.AttendanceMonthDao;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDayStatus;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * Created by GQR on 2017/10/18.
 */
@Service
@Transactional(readOnly = true)
public class AttendanceMonthService {

	@Autowired
	private TaskService taskService;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private final AttendanceMonthDao attendanceMonthDao;

	@Autowired
	public AttendanceMonthService(AttendanceMonthDao attendanceMonthDao) {
		this.attendanceMonthDao = attendanceMonthDao;
	}

	/**
	 * 根据年月 姓名查询
	 * 
	 * @param attendanceMonth
	 * @return
	 * @author Grace
	 * @date 2017年10月23日 上午10:39:06
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<AttendanceMonth> getAttendance(AttendanceMonth attendanceMonth) {
		return attendanceMonthDao.getAttendance(attendanceMonth);
	}

	/**
	 * 查询所有姓名唯一的信息
	 */
	public List<AttendanceMonth> getAllAttendance() {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		User user = UserUtils.getUser();
		attendanceMonth.setName(user.getName());
		return attendanceMonthDao.getNameAttendance(attendanceMonth);
	}

	/**
	 * 获取一条考勤的所有信息
	 */
	public AttendanceMonth getInformation(String id) {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		attendanceMonth.setId(id);
		attendanceMonth = attendanceMonthDao.getAttendanceEntity(attendanceMonth);
		return attendanceMonth;
	}

	/**
	 * 计算考勤状态和
	 */
	public List<AttendanceDayStatus> getDayStatusSum(List<AttendanceMonth> lists) {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		User user = UserUtils.getUser();
		attendanceMonth.setName(user.getName());
		List<AttendanceDayStatus> list = new ArrayList<AttendanceDayStatus>();
		for (AttendanceMonth list1 : lists) {
			AttendanceDayStatus attendanceDayStatus = new AttendanceDayStatus();
			// 正常出勤,出差-短期,出差-长期,加班,请假,其它带薪假,病假,--公休日,--法定节假日
			int normalDay = 0, travelDayShort = 0, travelDayLong = 0, overtimeDay = 0, leaveDay = 0, paidLeaveDay = 0,
					sickLeaveDay = 0;
			for (int j = 0; j < list1.getAttendanceStatus().size(); j++) {
				if ("1".equals(list1.getAttendanceStatus().get(j).getStatus())
						|| "2".equals(list1.getAttendanceStatus().get(j).getStatus())
						|| "3".equals(list1.getAttendanceStatus().get(j).getStatus())
						|| "4".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					normalDay++;
					attendanceDayStatus.setNormalDay(normalDay);
				}
				if ("2".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					travelDayShort++;
					attendanceDayStatus.setTravelDayShort(travelDayShort);
				}
				if ("3".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					travelDayLong++;
					attendanceDayStatus.setTravelDayLong(travelDayLong);
				}
				if ("4".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					overtimeDay++;
					attendanceDayStatus.setOvertimeDay(overtimeDay);
				}
				if ("5".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					leaveDay++;
					attendanceDayStatus.setLeaveDay(leaveDay);
				}
				if ("6".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					paidLeaveDay++;
					attendanceDayStatus.setPaidLeaveDay(paidLeaveDay);
				}
				if ("7".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					sickLeaveDay++;
					attendanceDayStatus.setSickLeaveDay(sickLeaveDay);
				}
			}
			list.add(attendanceDayStatus);
		}
		return list;
	}

	/**
	 * 我的 考勤查询分页
	 * 
	 * @param page
	 * @return
	 */
	public Page<AttendanceMonth> page(Page<AttendanceMonth> page) {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		attendanceMonth.setPage(page);
		User user = UserUtils.getUser();
		attendanceMonth.setName(user.getName());
		return attendanceMonthDao.getAttendancePage(attendanceMonth);
	}

	/**
	 * 首页考勤信息
	 * 
	 * @param page
	 * @return
	 */
	public Page<AttendanceMonth> attendanceHomeList(Page<AttendanceMonth> page) {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		attendanceMonth.setPage(page);
		User user = UserUtils.getUser();
		attendanceMonth.setName(user.getName());
		Page<AttendanceMonth> attendanceList = attendanceMonthDao.getAttendancePage(attendanceMonth);
		List<AttendanceMonth> lists = attendanceList.getList();
		List<AttendanceDayStatus> list = getDayStatusSum(lists);
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setAttendanceDayStatus(list.get(i));
		}
		return page;
	}

	/**
	 * 查看考勤（退回）：改变考勤状态
	 */
	@Transactional(rollbackFor = Exception.class)
	public AttendanceMonth getSendBackAttendaceStatus(AttendanceMonth attendanceMonth) {
		AttendanceMonth attendanceStatus = new AttendanceMonth();
		attendanceStatus.setId(attendanceMonth.getId());
		// 修改为提交状态
		attendanceStatus.setProcessStatus("1");
		attendanceMonthDao.update(attendanceStatus);
		return attendanceStatus;
	}

	/**
	 * 我的考勤任务
	 * 
	 * @param act
	 * @return
	 * @author Grace
	 * @date 2018年1月15日 上午10:13:53
	 */
	public List<AttendanceMonth> todoListAttendance(Act act) {
		String userId = UserUtils.getUser().getLoginName();// ObjectUtils.toString(UserUtils.getUser().getId());

		List<AttendanceMonth> resultList = new ArrayList<AttendanceMonth>();

		TaskQuery todoTaskQuery = taskService.createTaskQuery().taskAssignee(userId).active().includeProcessVariables()
				.orderByTaskCreateTime().desc();

		// 设置查询条件
		/*if (StringUtils.isNotBlank(act.getProcDefKey())) {
			todoTaskQuery.processDefinitionKey(act.getProcDefKey());
		}
		if (act.getBeginDate() != null) {
			todoTaskQuery.taskCreatedAfter(act.getBeginDate());
		}
		if (act.getEndDate() != null) {
			todoTaskQuery.taskCreatedBefore(act.getEndDate());a
		}*/

		// 查询列表
		List<Task> todoList = todoTaskQuery.list();

		for (Task task : todoList) {
			// 获取任务的procInsId
			String procInsId = ((TaskEntity) task).getProcessInstanceId();

			AttendanceMonth attendanceMonth = new AttendanceMonth();
			attendanceMonth.setProcInsId(procInsId);
			List<AttendanceMonth> attendanceMonthList = attendanceMonthDao.getAttendance(attendanceMonth);

			if (attendanceMonthList != null && attendanceMonthList.size() > 0) {

				attendanceMonth = attendanceMonthList.get(0);
				// 将task和流程变量都赋给这个reimburseModel对象
				Act actNew = new Act();
				actNew.setTask(task);
				actNew.setVars(task.getProcessVariables());
				actNew.setProcDef(ProcessDefCache.get(task.getProcessDefinitionId()));
				
				attendanceMonth.setAct(actNew);
				
				// 将该对象放到resultList中
				resultList.add(attendanceMonth);
			}
		}

		return resultList;
	}
}
