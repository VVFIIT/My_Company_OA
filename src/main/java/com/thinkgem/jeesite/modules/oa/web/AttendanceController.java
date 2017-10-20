package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDayStatus;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.oa.service.AttendanceMonthService;
import com.thinkgem.jeesite.modules.oa.service.AttendanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

//    private AttendanceMonth updateAttendanceMonth;

    @Autowired
    private AttendanceMonthService attendanceMonthService;

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
    @RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
    public String list(Model model) {
        List<AttendanceMonth> list = attendanceMonthService.getAllAttendance();
        List<AttendanceDayStatus> lists = attendanceMonthService.getDayStatusSum();
        System.out.println("_____________________" + lists);
        model.addAttribute("list", list);
        return "modules/oa/attendanceList";
    }

    /**
     * 添加考勤跳转
     */
    @RequestMapping(value = "insert")
    public String attendanceUpdate() {
        return "modules/oa/attendanceInsert";
    }

    /**
     * 查看所有考勤
     */
    @RequiresPermissions("oa:attendance:view")
    @RequestMapping(value = "showAll")
    public String showAllAttendance(AttendanceMonth attendance, Model model) {
        List<AttendanceMonth> attendancelist = attendanceService.attendanceShowAllService(attendance);
        model.addAttribute("list", attendancelist);
        return "modules/oa/attendanceShowAll";
    }


    /**
     * 添加考勤列表
     */
    @RequestMapping(value = "attendanceSearchList")
    public String attendanceList(AttendanceMonth attendanceMonth, Model model) {
        AttendanceMonth attendanceMonth1 = attendanceService.getAttendanceDateList(attendanceMonth);
        model.addAttribute("attendanceMonth1", attendanceMonth1);
        return "modules/oa/attendanceInsertList";
    }

    @ModelAttribute("attendanceMonth")
    public AttendanceMonth getAttendanceMonthModel() {
        return attendanceService.getDefaultAttendanceMonth();
    }

    /**
     * 提交考勤列表
     */
    @RequestMapping(value = "attendanceInsertList")
    public String attendanceInsert(Attendance attendance, Model model) {
        attendanceService.InsertAttendanceList(attendance);
        return "modules/oa/attendanceList";
    }

    /**
     * 查看个人考勤
     */
    @RequestMapping(value = "show")
    public String attendanceShow() {
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
     * 修改个人考勤
     */
    @RequestMapping(value = "form")
    public String form(AttendanceMonth attendanceMonth, Model model) {
        if (StringUtils.isNotBlank(attendanceMonth.getId())) {
//            attendanceMonth = attendanceService.getRecordList(attendanceMonth);
        }
        model.addAttribute("attendanceMonth", attendanceMonth);
        return "modules/oa/attendanceInsertList";
    }
}