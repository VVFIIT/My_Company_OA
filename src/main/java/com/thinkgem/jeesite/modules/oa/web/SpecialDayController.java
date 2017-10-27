package com.thinkgem.jeesite.modules.oa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.oa.entity.SpecialDay;
import com.thinkgem.jeesite.modules.oa.service.SpecialDayService;

/**
 * 特殊日子Controller
 *
 * @author Grace
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/specialDay")
public class SpecialDayController extends BaseController {

	@Autowired
	private SpecialDayService specialDayService;

	@RequestMapping(value = "findByType")
	public List<SpecialDay> findByType(SpecialDay specialDay) {
		specialDay.setType("holiday");
		return specialDayService.findByType(specialDay);
	}

}
