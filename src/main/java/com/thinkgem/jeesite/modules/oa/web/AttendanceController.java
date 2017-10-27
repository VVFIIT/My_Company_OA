package com.thinkgem.jeesite.modules.oa.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDayStatus;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.oa.helper.StringName;
import com.thinkgem.jeesite.modules.oa.service.AttendanceMonthService;
import com.thinkgem.jeesite.modules.oa.service.AttendanceService;

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
	 * 考勤首页数据显示
	 */
	@RequestMapping(value = { "list", "" })
	public String list(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Page<AttendanceMonth> page = attendanceMonthService.page(new Page<AttendanceMonth>(request, response));
		List<AttendanceMonth> lists = page.getList();
		List<AttendanceDayStatus> list = attendanceMonthService.getDayStatusSum();
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setAttendanceDayStatus(list.get(i));
		}
		AttendanceMonth attendanceMonth1 = attendanceService.getDefaultYearAndMonth();
		if (attendanceMonth1.getYear() == 0) {
			model.addAttribute("MODE", "noInsertMonth");
		} else {
			model.addAttribute("MODE", "yesInsertMonth");
		}
		model.addAttribute("page", page);
		return "modules/oa/attendanceList";
	}
	
	/**
	 * 添加考勤跳转
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "insert")
	public String attendanceUpdate(Model model, HttpServletRequest request, HttpServletResponse response) {
		AttendanceMonth attendanceMonth = attendanceService.getDefaultYearAndMonth();
		if (attendanceMonth.getYear() == 0) {
			Page<AttendanceMonth> page = attendanceMonthService.page(new Page<AttendanceMonth>(request, response));
			List<AttendanceMonth> lists = page.getList();
			List<AttendanceDayStatus> list = attendanceMonthService.getDayStatusSum();
			for (int i = 0; i < lists.size(); i++) {
				lists.get(i).setAttendanceDayStatus(list.get(i));
			}
			model.addAttribute("MODE", "noInsertMonth");
			model.addAttribute("page", page);
			return "modules/oa/attendanceList";
		} else {
			List<AttendanceMonth> list = attendanceService.getExistAttendanceMonth();
			HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
			attendanceService.getStartDateAndEndDate(hashMap);
			model.addAttribute("startYear", hashMap.get("startYear"));
			model.addAttribute("startMonth", hashMap.get("startMonth"));
			model.addAttribute("endYear", hashMap.get("endYear"));
			model.addAttribute("endMonth", hashMap.get("endMonth"));
			model.addAttribute("existAttendanceMonthList", list);
			model.addAttribute("attendanceMonth_Insert", attendanceMonth);
			return "modules/oa/attendanceInsert";
		}
	}
	
	/**
	 * 添加画面点击确定后出现考勤列表
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "attendanceSearchList")
	public String attendanceList(AttendanceMonth attendanceMonth, Model model) {
		AttendanceMonth attendanceMonth1 = attendanceService.getAttendanceDateList(attendanceMonth);
		AttendanceMonth attendanceMonth2 = attendanceService.insertPageDefaultAttendanceMonth(updateAttendanceMonth);
		model.addAttribute("attendanceMonth1", attendanceMonth1);
		model.addAttribute("attendanceMonth_InsertList", attendanceMonth2);
		return "modules/oa/attendanceInsertList";
	}
	
	/**
	 * 提交添加画面考勤列表
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "attendanceInsertList")
	public String attendanceInsert(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		attendanceService.InsertAttendanceList(attendanceMonth);
		Page<AttendanceMonth> page = attendanceMonthService.page(new Page<AttendanceMonth>(request, response));
		List<AttendanceMonth> lists = page.getList();
		List<AttendanceDayStatus> list = attendanceMonthService.getDayStatusSum();
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setAttendanceDayStatus(list.get(i));
		}
		AttendanceMonth attendanceMonth1 = attendanceService.getDefaultYearAndMonth();
		if (attendanceMonth1.getYear() == 0) {
			model.addAttribute("MODE", "noInsertMonth");
		} else {
			model.addAttribute("MODE", "yesInsertMonth");
		}
		model.addAttribute("page", page);
		return "modules/oa/attendanceList";
	}
	
	/**
	 * 查看某月考勤列表
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "searchAttendanceInformation")
	public String searchAttendanceInformation(AttendanceMonth attendanceMonth, Model model) {
		attendanceMonth = attendanceMonthService.getInformation(attendanceMonth.getId());
		AttendanceMonth attendanceMonth2 = attendanceService.updatePageDefaultAttendanceMonth(attendanceMonth);
		model.addAttribute("attendanceMonth", attendanceMonth);
		model.addAttribute("attendanceMonth_InsertList", attendanceMonth2);
		return "modules/oa/attendanceShowList";
	}

	/**
	 * 修改某月考勤列表
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "modifyAttendanceInformation")
	public String modifyAttendanceInformation(AttendanceMonth attendanceMonth, Model model) {
		attendanceMonth = attendanceMonthService.getInformation(attendanceMonth.getId());
		AttendanceMonth attendanceMonth2 = attendanceService.updatePageDefaultAttendanceMonth(attendanceMonth);
		model.addAttribute("attendanceMonth1", attendanceMonth);
		model.addAttribute("attendanceMonth_InsertList", attendanceMonth2);
		return "modules/oa/attendanceUpdateList";
	}
	
	/**
	 * 提交修改画面考勤列表
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "attendanceUpdateList")
	public String attendanceUpdate(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request, HttpServletResponse response) {
		attendanceService.updateAttendanceList(attendanceMonth);
		Page<AttendanceMonth> page = attendanceMonthService.page(new Page<AttendanceMonth>(request, response));
		List<AttendanceMonth> lists = page.getList();
		List<AttendanceDayStatus> list = attendanceMonthService.getDayStatusSum();
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setAttendanceDayStatus(list.get(i));
		}
		model.addAttribute("page", page);
		return "modules/oa/attendanceList";
	}
	
	/**
	 * 点击按钮返回首页
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "returnIndexPage")
	public String returnIndexPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		Page<AttendanceMonth> page = attendanceMonthService.page(new Page<AttendanceMonth>(request, response));
		List<AttendanceMonth> lists = page.getList();
		List<AttendanceDayStatus> list = attendanceMonthService.getDayStatusSum();
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setAttendanceDayStatus(list.get(i));
		}
		model.addAttribute("page", page);
		return "modules/oa/attendanceList";
	}

	/**
	 * 查询考勤状态
	 */
	@RequiresPermissions("oa:attendance:view")
	@RequestMapping(value = "showAll")
	public String showAllAttendance(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// 默认查询考勤状态，并分页
		Page<AttendanceMonth> page = new Page<AttendanceMonth>(request, response);
		attendanceMonth.setPage(page);
		Page<AttendanceMonth> returnPage = attendanceService.getAttendanceShowAllPage(attendanceMonth);
		model.addAttribute("page", returnPage);
		// 查询栏 默认显示的年月
		AttendanceMonth attendanceMonthReturn = attendanceService.getAttendanceMonth(attendanceMonth);
		model.addAttribute("attendanceShowAll", attendanceMonthReturn);
		return "modules/oa/attendanceShowAll";
	}

	@ModelAttribute("attendanceMonth")
	public AttendanceMonth getAttendanceMonthModel() {
		return this.updateAttendanceMonth;
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
	 * 提交个人考勤
	 */
	@RequestMapping(value = "checkProcessStatus")
	public String checkProcessStatus(String id, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		attendanceMonthService.updateProcessStatus(id);
		addMessage(redirectAttributes, "提交考勤成功");
		Page<AttendanceMonth> page = attendanceMonthService.page(new Page<AttendanceMonth>(request, response));
		List<AttendanceMonth> lists = page.getList();
		List<AttendanceDayStatus> list = attendanceMonthService.getDayStatusSum();
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setAttendanceDayStatus(list.get(i));
		}
		model.addAttribute("page", page);
		return "modules/oa/attendanceList";
	}

	/**
	 * 导出
	 */
	@RequestMapping(value = "showAllExport")
	public String attendanceShowAllExport(AttendanceMonth attendanceMonth, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			List<AttendanceMonth> list = attendanceMonthService.getAttendance(attendanceMonth);
			List<AttendanceDay> exportList = new ArrayList<AttendanceDay>();

			if (list.size() > 0 && list != null) {
				exportList = list.get(0).getAttendanceStatus();
				for (AttendanceDay attendanceDay : exportList) {
					String status = attendanceDay.getStatus();
					// 给考勤状态匹配常量类中的汉字
					switch (status) {
					case "1":
						attendanceDay.setStatus(StringName.s1);
						break;
					case "2":
						attendanceDay.setStatus(StringName.s2);
						break;
					case "3":
						attendanceDay.setStatus(StringName.s3);
						break;
					case "4":
						attendanceDay.setStatus(StringName.s4);
						break;
					case "5":
						attendanceDay.setStatus(StringName.s5);
						break;
					case "6":
						attendanceDay.setStatus(StringName.s6);
						break;
					case "7":
						attendanceDay.setStatus(StringName.s7);
						break;

					case "8":
						attendanceDay.setStatus(StringName.s8);
						break;
					case "9":
						attendanceDay.setStatus(StringName.s9);
						break;
					}
				}
			}
			String fileName = attendanceMonth.getYear() + "年" + attendanceMonth.getMonth() + "月" + list.get(0).getName()
					+ "考勤" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			new ExportExcel("员工考勤", AttendanceDay.class).setDataList(exportList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出员工考勤失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/modules/oa/attendanceShowAll";
	}

}
