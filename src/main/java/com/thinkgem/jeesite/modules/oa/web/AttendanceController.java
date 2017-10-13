package com.thinkgem.jeesite.modules.oa.web;


import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @RequestMapping(value = "attendanceUpdate")
    public String attendanceUpdate() {
        logger.debug("QQQQQQQQQQQQQQQQQ");
        return "modules/oa/attendanceUpdate";
    }
    /**
     * 查看考勤
     */
	@RequestMapping(value ="showAll")
    public String showAllAttendance(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/oa/attendanceShowAll";
	}
}