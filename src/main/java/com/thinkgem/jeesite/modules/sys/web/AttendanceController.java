package com.thinkgem.jeesite.modules.sys.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.service.AttendanceService;
@Controller
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
}
