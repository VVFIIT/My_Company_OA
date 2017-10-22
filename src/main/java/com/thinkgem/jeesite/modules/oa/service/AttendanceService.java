package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.oa.dao.AttendanceDao;
import com.thinkgem.jeesite.modules.oa.dao.AttendanceMonthDao;
import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

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
	
	@Autowired
	private AttendanceMonthDao attendanceMonthDao;
	
	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void getName() {
		attendanceDao.findAll();
		logger.debug("@@@@@@@@@@@@@@@@@");
		logger.debug("@@@@@@@@@@@@@@@@@");
	}

	/*
     * 添加考勤列表
     */
    public AttendanceMonth getAttendanceDateList(AttendanceMonth attendanceMonth){
    	int year = attendanceMonth.getYear();
    	int month = attendanceMonth.getMonth();
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
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
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
		attendanceMonth.setAttendanceStatus(attendanceDayList);
    	return attendanceMonth;
    }
    
    /*
     * 添加考勤跳转框年份和月份默认值
     */
    public AttendanceMonth getDefaultYearAndMonth(){
    	int defaultYear;
    	int defaultMonth;
    	AttendanceMonth attendanceMonth = new AttendanceMonth();
    	User user = UserUtils.getUser();
    	attendanceMonth.setName(user.getName());
    	List<AttendanceMonth> list = attendanceMonthDao.getAttendance(attendanceMonth);
    	if(list.size()==0) {
    		Calendar date = Calendar.getInstance();
    		int year = date.get(Calendar.YEAR);
    		int month = date.get(Calendar.MONTH)+1;
    		if(month==1) {
    			defaultYear = year-1;
    			defaultMonth = 12;
    		}else {
    			defaultYear = year;
        		defaultMonth = month-1;
    		}
    	}else {
    		int year = list.get(0).getYear();
        	int month = list.get(0).getMonth();
        	if(month==12) {
        		defaultYear = year+1;
        		defaultMonth = 1;
        	}else {
        		defaultYear = year;
        		defaultMonth = month;
        	}
    	}
    	attendanceMonth.setYear(defaultYear);
    	attendanceMonth.setMonth(defaultMonth);
    	return attendanceMonth;
    }
    
    /*
     * 查询考勤状态列表默认值
     */
    public AttendanceMonth getDefaultAttendanceMonth(AttendanceMonth attendanceMonth){
    	int defaultYear = attendanceMonth.getYear();
    	int defaultMonth = attendanceMonth.getMonth();
    	Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date dateDay = null;
		String strDateDay = defaultYear + "-" + defaultMonth;
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
			String strDateWeek = defaultYear + "-" + defaultMonth + "-" + i;
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
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
		attendanceMonth.getAttendanceHelper().updateAttendanceHelperStatus(attendanceDayList);
		attendanceMonth.setYear(defaultYear);
		attendanceMonth.setMonth(defaultMonth);
    	return attendanceMonth;
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
		for (int i = 0; i < strDate.length; i++) {
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

	/**
	 * 查询考勤状态
	 */
	public List<AttendanceMonth> getAttendanceShowAll(
			AttendanceMonth attendance) {
		Integer month = attendance.getMonth();
		Integer year = attendance.getYear();
		User user = new User();
		List<User> userList = userDao.findList(user);
		List<AttendanceMonth> returnList = new ArrayList<AttendanceMonth>();
		for (User userInformation : userList) {
			String name = userInformation.getName();
			AttendanceMonth attendanceInsert = new AttendanceMonth();
			if (month == null && year == null) {
				Calendar cal = Calendar.getInstance();
				int yearCurrent = cal.get(Calendar.YEAR); // 获取当前年份
				int monthCurrent = cal.get(Calendar.MONTH); // 获取当前月份
				attendanceInsert.setName(name);
				attendanceInsert.setYear(yearCurrent);
				attendanceInsert.setMonth(monthCurrent);
			} else {
				attendanceInsert.setName(name);
				attendanceInsert.setYear(year);
				attendanceInsert.setMonth(month);
			}
			List<AttendanceMonth> attendanceList = attendanceMonthDao
					.getAttendance(attendanceInsert);

			if (0 == attendanceList.size()) {
				// 根据姓名，年，月，没有记录，此人还没有提交过，页面需要显示 姓名 和 空状态
				attendanceList.add(attendanceInsert);
			}
			// 正常情况下 根据姓名，年，月，会精确查出一条记录
			returnList.add(attendanceList.get(0));
		}
		return returnList;
	}
	
	/**
	 * 考勤状态 默认显示的年和月
	 */
	public AttendanceMonth getDefaultAttendanceMoth(){		
		Calendar cal = Calendar.getInstance();
		int defaultYear = cal.get(Calendar.YEAR); // 获取当前年份
		int defaultMonth = cal.get(Calendar.MONTH); // 获取当前月份
		AttendanceMonth updateAttendanceMonth = new AttendanceMonth();
		updateAttendanceMonth.setYear(defaultYear);
    	updateAttendanceMonth.setMonth(defaultMonth);
    	return updateAttendanceMonth;
    }
	
	/**
	 * 查询指定年和月的考勤状态
	 */
	public AttendanceMonth getAttendanceMonth(AttendanceMonth attendance){		
		Integer month = attendance.getMonth();
		Integer year = attendance.getYear();
		AttendanceMonth updateAttendanceMonth = new AttendanceMonth();
		updateAttendanceMonth.setYear(year);
    	updateAttendanceMonth.setMonth(month);
    	return updateAttendanceMonth;
    }
}
