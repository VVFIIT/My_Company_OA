package com.thinkgem.jeesite.modules.oa.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.oa.entity.TestAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.service.AttendanceService;
@Controller
@RequestMapping(value = "${adminPath}/oa/attendance")
public class AttendanceController extends BaseController{

    @Autowired
    private AttendanceService attendanceService;

    /**
     * MongoDB测试
     */
    @RequestMapping(value = "${adminPath}/mongo", method = RequestMethod.GET)
    public String mongo(HttpServletRequest request, HttpServletResponse response, Model model) {
        attendanceService.getName();
        logger.debug("MongoDB开始");
        return "MongoDB开始";
    }


    @RequestMapping(value = {"list", ""})
    public String list(TestAudit testAudit, HttpServletRequest request, HttpServletResponse response, Model model) {
//		User user = UserUtils.getUser();
//		if (!user.isAdmin()){
//			testAudit.setCreateBy(user);
//		}
//        Page<TestAudit> page = attendanceService.findPage(new Page<TestAudit>(request, response), testAudit);
//        model.addAttribute("page", page);
        return "modules/oa/attendanceList";
    }
}
