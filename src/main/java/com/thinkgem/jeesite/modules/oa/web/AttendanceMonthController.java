package com.thinkgem.jeesite.modules.oa.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.oa.service.AttendanceMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GQR on 2017/10/18.
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class AttendanceMonthController  extends BaseController {

    @Autowired
    private AttendanceMonthService attendanceMonthService;

    /**
     * MongoDB测试
     */
    @RequestMapping(value = "mongo", method = RequestMethod.GET)
    public String mongo(HttpServletRequest request, HttpServletResponse response, Model model) {
        AttendanceMonth temp = attendanceMonthService.getName();
        logger.debug("MongoDB开始");
        return "MongoDB开始";
    }
}
