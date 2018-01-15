package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.act.entity.Act;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.oa.helper.StringName;
import com.thinkgem.jeesite.modules.oa.service.AttendanceMonthService;
import com.thinkgem.jeesite.modules.oa.service.AttendanceService;
import com.thinkgem.jeesite.modules.oa.helper.EmailUtil;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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

	@Autowired
	private AttendanceMonthService attendanceMonthService;

	/**
	 * 考勤首页数据显示
	 */
	@RequestMapping(value = "list")
	public String list(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// 返回首页并将画面信息传到画面
		Page<AttendanceMonth> page = attendanceMonthService
				.attendanceHomeList(new Page<AttendanceMonth>(request, response));
		model.addAttribute("page", page);
		// 判断是否存在可以添加的年份月份，并把MODE传到画面
		attendanceService.insertMonthToModel(model);
		return "modules/oa/attendanceList";
	}

	/**
	 * 添加考勤跳转
	 * 
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "insert")
	public String attendanceUpdate(Model model, HttpServletRequest request, HttpServletResponse response) {
		// 获取添加考勤跳转后的年份和月份默认值
		AttendanceMonth attendanceMonth = attendanceService.getDefaultYearAndMonth();
		// 如果没有可以添加的考勤月份，那么返回首页，并通过model把MODE传给画面
		if (attendanceMonth.getYear() == 0) {
			Page<AttendanceMonth> page = attendanceMonthService
					.attendanceHomeList(new Page<AttendanceMonth>(request, response));
			model.addAttribute("MODE", "noInsertMonth");
			model.addAttribute("page", page);
			return "modules/oa/attendanceList";
		} else {
			// 通过用户名找到所有该用户填写过的考勤记录并通过model传到画面
			List<AttendanceMonth> list = attendanceService.getExistAttendanceMonth();
			// 通过HashMap获取用户的入职年月和截止年月并通过model传到画面供前端JS作逻辑判断
			HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
			attendanceService.getStartDateAndEndDate(hashMap);
			model.addAttribute("startYear", hashMap.get("startYear"));
			model.addAttribute("startMonth", hashMap.get("startMonth"));
			model.addAttribute("endYear", hashMap.get("endYear"));
			model.addAttribute("endMonth", hashMap.get("endMonth"));
			model.addAttribute("existAttendanceMonthList", list);
			// 将跳转后的年份和月份默认值和attendanceMonth_InsertDate绑定并通过model传到JSP
			model.addAttribute("attendanceMonth_InsertDate", attendanceMonth);
			return "modules/oa/attendanceInsert";
		}
	}

	/**
	 * 添加画面点击确定后出现考勤列表
	 * 
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "attendanceSearchList")
	public String attendanceList(AttendanceMonth attendanceMonth, Model model) {
		// 获取添加画面考勤列表默认值
		AttendanceMonth defaultInsertAttendanceMonth = attendanceService
				.insertPageDefaultAttendanceMonth(attendanceMonth);
		// 传到JSP
		model.addAttribute("attendanceMonth_InsertList", defaultInsertAttendanceMonth);
		return "modules/oa/attendanceInsertList";
	}

	/**
	 * 提交添加画面考勤列表
	 * 
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "attendanceInsertList")
	public String attendanceInsert(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		boolean isExistInDB = attendanceService.isExistAttendanceMonth(attendanceMonth);
		if (isExistInDB) {
			addMessage(model, "添加失败！您已经添加过" + attendanceMonth.getYear() + "年" + attendanceMonth.getMonth() + "月的考勤");
		} else {
			// 将表单中的值插入DB
			attendanceService.InsertAttendanceList(attendanceMonth);
			addMessage(model, "添加" + attendanceMonth.getYear() + "年" + attendanceMonth.getMonth() + "月考勤成功");
		}
		// 返回首页并将画面信息传到画面
		Page<AttendanceMonth> page = attendanceMonthService
				.attendanceHomeList(new Page<AttendanceMonth>(request, response));
		model.addAttribute("page", page);
		// 判断是否存在可以添加的年份月份，并把MODE传到画面
		attendanceService.insertMonthToModel(model);
		return "modules/oa/attendanceList";
	}

	/**
	 * 查看某月考勤列表
	 * 
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "searchAttendanceInformation")
	public String searchAttendanceInformation(AttendanceMonth attendanceMonth, Model model) {
		// 通过UUID获取对应月份的attendanceMonth对象
		attendanceMonth = attendanceMonthService.getInformation(attendanceMonth.getId());
		// 传到JSP
		model.addAttribute("attendanceMonth_ShowList", attendanceMonth);
		try {
			// 发送邮件
			User user = UserUtils.getUser();
			EmailUtil.sendTextEmail("jiqing.jiang@hongshenol.com", "1606528102@qq.com", "出差任务", "你好");
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
		// MailUtil.singleMail("1606528102@qq.com","进度提示","你好");
		return "modules/oa/attendanceShowList";
	}

	/**
	 * 修改某月考勤列表
	 * 
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "modifyAttendanceInformation")
	public String modifyAttendanceInformation(AttendanceMonth attendanceMonth, Model model) {
		// 通过UUID获取对应月份的attendanceMonth对象
		attendanceMonth = attendanceMonthService.getInformation(attendanceMonth.getId());
		// 获取修改画面考勤列表默认地点和考勤状态
		AttendanceMonth attendanceMonth1 = attendanceService.updatePageDefaultAttendanceMonth(attendanceMonth);
		// 获取修改画面考勤列表默认日期星期
		attendanceMonth1.setAttendanceStatus(attendanceMonth.getAttendanceStatus());
		// 将最后的默认AttendanceMonth对象传到JSP
		model.addAttribute("attendanceMonth_UpdateList", attendanceMonth1);
		return "modules/oa/attendanceUpdateList";
	}

	/**
	 * 提交修改画面考勤列表
	 * 
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "attendanceUpdateList")
	public String attendanceUpdate(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// 修改考勤列表
		attendanceService.updateAttendanceList(attendanceMonth);
		// 修改成功，返回首页时弹窗提示
		addMessage(model, "修改" + attendanceMonth.getYear() + "年" + attendanceMonth.getMonth() + "月考勤成功");
		// 返回首页并将画面信息传到画面
		Page<AttendanceMonth> page = attendanceMonthService
				.attendanceHomeList(new Page<AttendanceMonth>(request, response));
		model.addAttribute("page", page);
		// 判断是否存在可以添加的年份月份，并把MODE传到画面
		attendanceService.insertMonthToModel(model);
		return "modules/oa/attendanceList";
	}

	/**
	 * 点击按钮返回首页
	 * 
	 * @author Meng Lingshuai
	 */
	@RequestMapping(value = "returnIndexPage")
	public String returnIndexPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		// 返回首页并将画面信息传到画面
		Page<AttendanceMonth> page = attendanceMonthService
				.attendanceHomeList(new Page<AttendanceMonth>(request, response));
		model.addAttribute("page", page);
		// 判断是否存在可以添加的年份月份，并把MODE传到画面
		attendanceService.insertMonthToModel(model);
		return "modules/oa/attendanceList";
	}

	@ModelAttribute("attendanceMonth")
	public AttendanceMonth getAttendanceMonthModel() {
		return new AttendanceMonth();
	}

	/**
	 * 查看考勤：查看个人考勤
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
	@RequestMapping(value = "submitOwnAttendance")
	public String submitOwnAttendance(String id, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		Page<AttendanceMonth> page = attendanceService.submitOwnAttendance(id, redirectAttributes, request, response);
		model.addAttribute("page", page);
		addMessage(redirectAttributes, "提交考勤成功");

		// 判断是否存在可以添加的年份月份，并把MODE传到画面
		attendanceService.insertMonthToModel(model);
		return "modules/oa/attendanceList";
	}

	/**
	 * 导出考勤
	 * 
	 * @param attendanceMonth
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @author Grace
	 * @date 2017年10月27日 下午5:27:25
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

	/**
	 * 查询考勤：默认显示
	 */
	@RequestMapping(value = "showAll")
	public String showAllAttendanceDefault(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// 查询默认考勤 并 分页
		Page<AttendanceMonth> page = new Page<AttendanceMonth>(request, response);
		attendanceMonth.setPage(page);
		Page<AttendanceMonth> defaultAttendance = attendanceService
				.getAttendanceShowAllDefault(new Page<User>(request, response), attendanceMonth);

		model.addAttribute("page", defaultAttendance);
		// 查询默认考勤的年和月
		AttendanceMonth defaultAttendanceDate = attendanceService.getAttendanceDateDefault();
		model.addAttribute("attendanceShowAll", defaultAttendanceDate);
		return "modules/oa/attendanceShowAll";
	}

	/**
	 * 查询考勤：根据年和月显示
	 */
	@RequestMapping(value = "showAllExact")
	public String showAllAttendanceExact(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// 根据年和月查询考勤并分页
		Page<AttendanceMonth> page = new Page<AttendanceMonth>(request, response);
		attendanceMonth.setPage(page);
		Page<AttendanceMonth> exactAttendance = attendanceService
				.getAttendanceShowAllExact(new Page<User>(request, response), attendanceMonth);

		model.addAttribute("page", exactAttendance);
		// 根据查询的年和月显示
		AttendanceMonth exactAttendanceDate = attendanceService.getAttendanceDateExact(attendanceMonth);
		model.addAttribute("attendanceShowAll", exactAttendanceDate);
		return "modules/oa/attendanceShowAll";
	}

	/**
	 * 查看考勤：退回考勤
	 */
	@RequestMapping(value = "sendBack")
	public String sendBackAttendanceStatus(AttendanceMonth attendanceMonth, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		attendanceMonthService.getSendBackAttendaceStatus(attendanceMonth);
		addMessage(model, "退回成功");
		// 显示考勤并分页
		Page<AttendanceMonth> page = new Page<AttendanceMonth>(request, response);
		attendanceMonth.setPage(page);
		Page<AttendanceMonth> backAttendance = attendanceService
				.getAttendanceShowAllExact(new Page<User>(request, response), attendanceMonth);
		model.addAttribute("page", backAttendance);
		// 显示年和月
		AttendanceMonth backAttendanceDate = attendanceService.getAttendanceDateExact(attendanceMonth);
		model.addAttribute("attendanceShowAll", backAttendanceDate);
		return "modules/oa/attendanceShowAll";
	}

	/**
	 * 我的考勤任务
	 * 
	 * @param act
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author Grace
	 * @date 2018年1月15日 上午10:15:28
	 */
	@RequestMapping(value = { "toDo" })
	public String todoList(Act act, HttpServletResponse response, Model model) throws Exception {
		List<AttendanceMonth> list = attendanceMonthService.todoListAttendance(act);
		model.addAttribute("list", list);
		if (UserUtils.getPrincipal().isMobileLogin()) {
			return renderString(response, list);
		}
		return "modules/oa/attendanceToDoList";

	}

	/**
	 * 去审批页面
	 * 
	 * @param act
	 * @param resourceName
	 * @param request
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author Grace
	 * @date 2018年1月15日 下午4:25:58
	 */
	@RequestMapping(value = "toApproval")
	public String form(Act act, String resourceName, HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {

		if (StringUtils.isNoneBlank(resourceName)) {
			String resource = StringUtils.split(resourceName, ".")[0];
			if ("attendance_audit".equals(resource)) {

				AttendanceMonth attendanceMonth = new AttendanceMonth();
				attendanceMonth.setProcInsId(act.getProcInsId());
				List<AttendanceMonth> attendanceMonthList = attendanceMonthService.getAttendance(attendanceMonth);
				if (attendanceMonthList != null && attendanceMonthList.size() > 0) {
					attendanceMonth = attendanceMonthList.get(0);
				}
				model.addAttribute("attendanceMonth", attendanceMonth);
			}
		}

		model.addAttribute("act", act);
		return "modules/oa/attendanceApproval";

	}

}
