package com.thinkgem.jeesite.modules.oa.web;


import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
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


    /**
     * MongoDB测试
     */
    @RequestMapping(value = "mongo1", method = RequestMethod.GET)
    public String mongo(HttpServletRequest request, HttpServletResponse response, Model model) {
        attendanceService.getName();
        logger.debug("MongoDB开始");
        return "MongoDB开始";
    }


    @RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
    public String list(Attendance attendance, Model model) {
//        insertAtt();
        List<Attendance> list = attendanceService.getAllAttendance(attendance);
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
    public String showAllAttendance(Attendance attendance, Model model) {
//    	attendance.setName("郭庆荣");
//    	List<Attendance> list = attendanceService.attendanceShowAllService(attendance);
//    	model.addAttribute("list", list);
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
     * test insert
     */
    @RequestMapping(value = "insertAtt")
    public void insertAtt() {
        attendanceService.insertAtt();
    }

    /**
     * 根据条件查询
     */
    @RequestMapping(value = "getAttendance")
    public List<Attendance> getAttendance(Attendance attendance) {
        return attendanceService.getAttendance(attendance);
    }

    /**

     * 查询所有
     */
    @RequestMapping(value = "getAllAttendance")
    public List<Attendance> getAllAttendance(Attendance attendance) {
        List<Attendance> list = attendanceService.getAllAttendance(attendance);
        return list;
    }

    /**
     * test delete
     */
    @RequestMapping(value = "delete")
    public void delete() {
        attendanceService.delete();
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
	 * test update
	 */
	@RequestMapping(value = "update")
	public void update() {
		attendanceService.update();
	}

}