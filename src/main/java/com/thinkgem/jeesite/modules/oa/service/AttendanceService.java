package com.thinkgem.jeesite.modules.oa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.oa.dao.AttendanceDao;
import com.thinkgem.jeesite.modules.oa.entity.Attendance;

/**
 * 考勤Service
 */
@Service
@Transactional(readOnly = true)
public class AttendanceService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AttendanceDao attendanceDao;

    @Transactional(readOnly = false)
    public void getName() {
        attendanceDao.findAll();
        logger.debug("@@@@@@@@@@@@@@@@@");
        logger.debug(attendanceDao.findAll().getId());
        logger.debug("@@@@@@@@@@@@@@@@@");
    }
    
    /*
     * 添加考勤列表
     */
    public List<String> getAttendanceDateList(Attendance attendance){
    	String month = attendance.getMonth();
		String year = attendance.getYear();
    	Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		String strDate = year + "-" + month;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date); 
		int daysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		ArrayList<String> dayslist = new ArrayList<String>();
		for(int i=1; i<=daysCount; i++) {
			String day = month+"月"+i+"日";
			dayslist.add(day);
		}
		return dayslist;
    }
}
