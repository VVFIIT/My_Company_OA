package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDayStatus;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.oa.service.AttendanceMonthService;
import com.thinkgem.jeesite.modules.oa.service.AttendanceService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 考勤Controller
 *
 * @author mojun
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/attendance")
public class AttendanceController extends BaseController {

	@Autowired
	private AttendanceService attendanceService;

	private AttendanceMonth updateAttendanceMonth;

	@Autowired
	private AttendanceMonthService attendanceMonthService;

	public AttendanceController() {
		this.updateAttendanceMonth = new AttendanceMonth();
	}

	/**
	 * MongoDB测试
	 */
	@RequestMapping(value = "mongo1", method = RequestMethod.GET)
	public String mongo(HttpServletRequest request, HttpServletResponse response, Model model) {
		attendanceService.getName();
		logger.debug("MongoDB开始");
		return "MongoDB开始";
	}

	/**
	 * 考勤首页数据显示
	 */
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	public String list(Model model) {
		List<AttendanceMonth> list = attendanceMonthService.getAllAttendance();
		List<AttendanceDayStatus> lists = attendanceMonthService.getDayStatusSum();
		for (int i = 0; i < lists.size(); i++) {
			list.get(i).setAttendanceDayStatus(lists.get(i));
		}
		model.addAttribute("list", list);
		return "modules/oa/attendanceList";
	}

	/**
	 * 添加考勤跳转
	 */
	@RequestMapping(value = "insert")
	public String attendanceUpdate(Model model) {
		AttendanceMonth attendanceMonth = attendanceService.getDefaultYearAndMonth();
		model.addAttribute("attendanceMonth_Insert", attendanceMonth);
		return "modules/oa/attendanceInsert";
	}

	/**
	 * 查询考勤状态
	 */
	@RequiresPermissions("oa:attendance:view")
	@RequestMapping(value = "showAll")
	public String showAllAttendance(AttendanceMonth attendance, Model model) {
		AttendanceMonth attendanceMonth = attendanceService.getAttendanceMonth(attendance);
		List<AttendanceMonth> attendancelist = attendanceService.getAttendanceShowAll(attendance);
		model.addAttribute("list", attendancelist);
		model.addAttribute("attendanceShowAll", attendanceMonth);
		return "modules/oa/attendanceShowAll";
	}

	/**
	 * 添加考勤列表
	 */
	@RequestMapping(value = "attendanceSearchList")
	public String attendanceList(AttendanceMonth attendanceMonth, Model model) {
		AttendanceMonth attendanceMonth1 = attendanceService.getAttendanceDateList(attendanceMonth);
		AttendanceMonth attendanceMonth2 = attendanceService.getDefaultAttendanceMonth(updateAttendanceMonth);
		model.addAttribute("attendanceMonth1", attendanceMonth1);
		model.addAttribute("attendanceMonth_InsertList", attendanceMonth2);
		return "modules/oa/attendanceInsertList";
	}

	@ModelAttribute("attendanceMonth")
	public AttendanceMonth getAttendanceMonthModel() {
		return this.updateAttendanceMonth;
	}

	/**
	 * 提交考勤列表
	 */
	@RequestMapping(value = "attendanceInsertList")
	public String attendanceInsert(AttendanceMonth attendanceMonth, Model model) {
		attendanceService.InsertAttendanceList(attendanceMonth);
		List<AttendanceMonth> list = attendanceMonthService.getAllAttendance();
		List<AttendanceDayStatus> lists = attendanceMonthService.getDayStatusSum();
		for (int i = 0; i < lists.size(); i++) {
			list.get(i).setAttendanceDayStatus(lists.get(i));
		}
		model.addAttribute("list", list);
		return "modules/oa/attendanceList";
	}

	/**
	 * 查看个人考勤
	 */
	@RequestMapping(value = "show")
	public String attendanceShow(AttendanceMonth attendance, Model model) {
		List<AttendanceMonth> list = attendanceService.getAttendanceShow(attendance);
		AttendanceMonth attendanceMonth = list.get(0);
		model.addAttribute("attendanceMonth", attendanceMonth);
		return "modules/oa/attendanceShow";
	}

	/**
	 * test getAttendanceByDate
	 */
	@RequestMapping(value = "getAttendanceByDate")
	public void getAttendanceByDate() {
		attendanceService.getAttendanceByDate();
	}

	/**
	 * 查看个人考勤详情
	 */
	@RequestMapping(value = "insertList")
	public String insertList() {

		return "modules/oa/attendanceInsert";
	}

	/**
	 * 修改个人考勤
	 */
	@RequestMapping(value = "modifyAttendanceInformation")
	public String modifyAttendanceInformation(AttendanceMonth attendanceMonth, Model model) {
		if (StringUtils.isNotBlank(attendanceMonth.getId())) {
			// attendanceMonth =
			// attendanceService.getRecordList(attendanceMonth);
		}
		// model.addAttribute("attendanceMonth", attendanceMonth);
		return "modules/oa/attendanceInsertList";
	}

	/**
	 * 提交个人考勤
	 */
	@RequestMapping(value = "checkProcessStatus")
	public String checkProcessStatus(AttendanceMonth attendanceMonth, RedirectAttributes redirectAttributes,
			Model model) {
		if (!"1".equals(attendanceMonth.getProcessStatus())) {
			attendanceMonthService.updateProcessStatus(attendanceMonth);
		} else if (!"2".equals(attendanceMonth.getProcessStatus())) {
			attendanceMonthService.updateProcessStatus(attendanceMonth);
		}
		// else if ("3".equals(attendanceMonth.getProcessStatus())){
		//
		// }
		addMessage(redirectAttributes, "提交考勤成功");
		List<AttendanceMonth> list = attendanceMonthService.getAllAttendance();
		List<AttendanceDayStatus> lists = attendanceMonthService.getDayStatusSum();
		for (int i = 0; i < lists.size(); i++) {
			list.get(i).setAttendanceDayStatus(lists.get(i));
		}
		model.addAttribute("list", list);
		return "modules/oa/attendanceList";
	}

	/**
	 * 导出
	 */
	@RequestMapping(value = "showAllExport")
	public String attendanceShowAllExport(AttendanceMonth attendanceMonth, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			// 临时测试数据
			attendanceMonth.setMonth(5);
			attendanceMonth.setName("张大天");
			attendanceMonth.setYear(2014);

			List<AttendanceMonth> list = attendanceMonthService.getAttendance(attendanceMonth);
			List<AttendanceDay> exportList = new ArrayList<AttendanceDay>();
			if (list.size() > 0 && list != null) {
				exportList = list.get(0).getAttendanceStatus();
			}
			String fileName = attendanceMonth.getYear() + "年" + attendanceMonth.getMonth() + "月"
					+ attendanceMonth.getName() + "考勤" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			new ExportExcel("员工考勤", AttendanceDay.class).setDataList(exportList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出员工考勤失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/modules/oa/attendanceShowAll";
	}

}