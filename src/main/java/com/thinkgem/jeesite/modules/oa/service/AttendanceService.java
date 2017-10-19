package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.modules.oa.dao.AttendanceDao;
import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 考勤Service
 *
 * @author mojun
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AttendanceService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AttendanceDao attendanceDao;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void getName() {
		attendanceDao.findAll();
		logger.debug("@@@@@@@@@@@@@@@@@");
		logger.debug("@@@@@@@@@@@@@@@@@");
	}

	/*
     * 添加考勤列表
     */
    public List<AttendanceDay> getAttendanceDateList(Attendance attendance){
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
		ArrayList<AttendanceDay> attendanceDayList = new ArrayList<AttendanceDay>();
		for(int i=1; i<=daysCount; i++) {
			Date dateWeek = null;
			String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
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
			String week = weekOfDays[w];
			String defaultStatus = null;
			if("星期六".equals(week) || "星期日".equals(week)) {
				defaultStatus = "公休日";
			}else {
				defaultStatus = "正常出勤";
			}
			AttendanceDay attendanceInsert = new AttendanceDay();
			attendanceInsert.setDate(i);
			attendanceInsert.setWeek(week);
			attendanceInsert.setStatus(defaultStatus);
			attendanceDayList.add(attendanceInsert);
		}
		return attendanceDayList;
    }
    
    /*
     * 插入考勤列表
     */
    public void InsertAttendanceList(Attendance attendance){
    	String[] strDate = attendance.getDate().split(",");
		String[] strWeek = attendance.getWeek().split(",");
		String[] strCity = attendance.getCity().split(",");
		String[] strAttStatus = attendance.getAttStatus().split(",");
		ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
		for(int i = 0; i<strDate.length; i++) {
			Attendance strAttendance = new Attendance();
			strAttendance.setDate(strDate[i]);
			strAttendance.setWeek(strWeek[i]);
			strAttendance.setCity(strCity[i]);
			strAttendance.setAttStatus(strAttStatus[i]);
			attendanceList.add(strAttendance);
		}
    }

	/**
	 * 插入考勤实体
	 */
	public void insertAtt() {
		Attendance attendance = new Attendance();
		attendance.setId(UUID.randomUUID().toString());
		attendance.setName("张大笋");
		attendance.setMonth("12");
		attendance.setYear("2017");
		attendanceDao.insert(attendance);
	}

	/**
	 * 查询根据姓名
	 */
	public List<Attendance> getAttendance(Attendance attendance) {
		attendance.setName("王");
		List<Attendance> attendanceList = attendanceDao.getAttendance(attendance);
		return attendanceList;
	}

	/**
	 * 查询所有
	 */
	public List<Attendance> getAllAttendance(Attendance attendance) {
		List<Attendance> attendanceList = attendanceDao.getAllAttendance(attendance);
		return attendanceList;
	}

	/**
	 * 更新状态
	 */
	public void update() {
		Attendance attendance = new Attendance();
		attendance.setName("张大跑");
		attendanceDao.update(attendance);
	}

	/**
	 * 删除
	 */
	public void delete() {
		Attendance attendance = new Attendance();
		attendance.setId("104856a8-05b9-d878-7311-289817711c8b");
		attendanceDao.delete(attendance);
	}

	/**
	 * 根据年月 查询
	 */
	public List<Attendance> getAttendanceByDate() {
		Attendance attendance = new Attendance();
		attendance.setYear("2017");
		attendance.setMonth("12");
		List<Attendance> attendanceList = attendanceDao.getAttendanceByDate(attendance);
		return attendanceList;
	}
}
