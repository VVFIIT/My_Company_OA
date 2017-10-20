package com.thinkgem.jeesite.modules.oa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.service.AttendanceMonthService;

/**
 * Created by GQR on 2017/10/18.
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class AttendanceMonthController extends BaseController {

	@Autowired
	private AttendanceMonthService attendanceMonthService;

}
