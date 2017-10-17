package com.thinkgem.jeesite.modules.oa.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import com.thinkgem.jeesite.modules.oa.service.AttendanceService;

@Controller
@RequestMapping(value = "${adminPath}/oa/attendance")
public class AttendanceController extends BaseController {

    @Autowired
    private AttendanceService attendanceService;


    /**
     * MongoDB测试
     */
    @RequestMapping(value = "mongo", method = RequestMethod.GET)
    public String mongo(HttpServletRequest request, HttpServletResponse response, Model model) {
        attendanceService.getName();
        logger.debug("MongoDB开始");
        return "MongoDB开始";
    }


    @RequestMapping(value = {"list", ""})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/oa/attendanceList";
    }

    /**
     * 查看跳转
     */
    @RequestMapping(value = "insert")
    public String attendanceUpdate() {
        return "modules/oa/attendanceInsert";
    }
    
    /**
     * 查看考勤
     */
	@RequestMapping(value = "showAll")
    public String showAllAttendance(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/oa/attendanceShowAll";
	}
	
	/**
	 * 添加考勤列表
	 */
	@RequestMapping(value = "attendanceSearchList")
	public String attendanceList(Attendance attendance, Model model) {
		List<Attendance> attendanceList = attendanceService.getAttendanceDateList(attendance);
		model.addAttribute("attendanceList", attendanceList);
		return "modules/oa/attendanceUpdate";
	}
	
	@ModelAttribute("attendance")
	public Attendance getAttendanceModel() {
		return new Attendance();
	}
	
	/**
	 * 提交考勤列表
	 */
	@RequestMapping(value = "attendanceInsertList")
	public String attendanceInsert(Attendance attendance, Model model) {
		
		return "modules/oa/attendanceList";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "getAttendance")
	public List<Attendance> getAttendance(Attendance attendance) {
		return attendanceService.getAttendance(attendance);
	}
}