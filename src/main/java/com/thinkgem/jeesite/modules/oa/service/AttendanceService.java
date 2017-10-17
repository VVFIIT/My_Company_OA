package com.thinkgem.jeesite.modules.oa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
		logger.debug("@@@@@@@@@@@@@@@@@");
	}

	/*
	 * 添加考勤列表
	 */
	public List<Attendance> getAttendanceDateList(Attendance attendance) {
		String month = attendance.getMonth();
		String year = attendance.getYear();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date dateDay = null;
		String strDateDay = year + "-" + month;
		try {
			dateDay = format.parse(strDateDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(dateDay);
		int daysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
		for (int i = 1; i <= daysCount; i++) {
			String day = month + "月" + i + "日";
			Date dateWeek = null;
			String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
			String strDateWeek = year + "-" + month + "-" + i;
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-DD");
			try {
				dateWeek = format1.parse(strDateWeek);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(dateWeek);
			int w = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
			Attendance attendanceInsert = new Attendance();
			attendanceInsert.setDate(day);
			attendanceInsert.setWeek(weekOfDays[w]);
			attendanceList.add(attendanceInsert);
		}
		return attendanceList;
	}

	/*
	 * 插入考勤实体
	 */
	public void insertAtt() {
		Attendance attendance = new Attendance();
		attendance.setId(UUID.randomUUID());
		attendance.setName("王二麻子");
		attendanceDao.insert(attendance);
	}

	/*
	 * 查询
	 */
	public List<Attendance> getAttendance(Attendance attendance) {
		List<Attendance> attendanceList = attendanceDao.getAttendanceByName(attendance);
		return attendanceList;
	}
}
