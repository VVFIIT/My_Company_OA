package com.thinkgem.jeesite.modules.oa.service;

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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.oa.dao.AttendanceMonthDao;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.oa.helper.AttendanceHelper;
import com.thinkgem.jeesite.modules.oa.helper.AttendanceUtils;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

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
	private AttendanceMonthDao attendanceMonthDao;
	
	@Autowired
	private AttendanceMonthService attendanceMonthService;
	
	@Autowired
	private UserDao userDao;

	/**
     * 添加画面的考勤列表
     * @author Meng Lingshuai
     */
    public AttendanceMonth getAttendanceDateList(AttendanceMonth attendanceMonth){
    	int year = attendanceMonth.getYear();
    	int month = attendanceMonth.getMonth();
    	List<AttendanceDay> attendanceDayList = getAttendanceStatusByYearAndMonth(year, month);
		attendanceMonth.setAttendanceStatus(attendanceDayList);
    	return attendanceMonth;
    }
    
    /**
     * 将考勤列表插入DB
     * @author Meng Lingshuai
     */
    public void InsertAttendanceList(AttendanceMonth attendanceMonth){
    	List<AttendanceDay> attendanceStatus = attendanceMonth.getAttendanceStatus();
    	AttendanceMonth attendanceMonth1 = changeValueToAtendanceStatus(attendanceMonth, attendanceStatus);
    	attendanceMonth1.setId(UUID.randomUUID().toString());
    	User user = UserUtils.getUser();
    	attendanceMonth1.setName(user.getName());
    	attendanceMonth1.setProcessStatus("1");
    	attendanceMonth1.setDepartment(user.getOffice().getName());
    	attendanceMonthDao.insert(attendanceMonth1);
	}
    
    /**
     * 将考勤列表修改
     * @author Meng Lingshuai
     */
    public void updateAttendanceList(AttendanceMonth attendanceMonth){
    	AttendanceMonth attendanceMonth1 = attendanceMonthService.getInformation(attendanceMonth.getId());
    	List<AttendanceDay> attendanceStatus = attendanceMonth1.getAttendanceStatus();
    	attendanceMonth.setMonth(attendanceMonth1.getMonth());
    	AttendanceMonth attendanceMonth2 = changeValueToAtendanceStatus(attendanceMonth, attendanceStatus);
    	attendanceMonthDao.update(attendanceMonth2);
    }
    
    /**
     * 添加画面考勤列表默认值
     * @author Meng Lingshuai
     */
    public AttendanceMonth insertPageDefaultAttendanceMonth(AttendanceMonth attendanceMonth){
    	int defaultYear = attendanceMonth.getYear();
    	int defaultMonth = attendanceMonth.getMonth();
    	List<AttendanceDay> attendanceDayList = getAttendanceStatusByYearAndMonth(defaultYear, defaultMonth);
		AttendanceMonth attendanceMonth1 = new AttendanceMonth();
		attendanceMonth1.getAttendanceHelper().updateAttendanceHelperStatus(attendanceDayList);
		attendanceMonth1.setYear(defaultYear);
		attendanceMonth1.setMonth(defaultMonth);
    	return attendanceMonth1;
    }
    
    /**
     * 修改画面考勤列表默认值
     * @author Meng Lingshuai
     */
    public AttendanceMonth updatePageDefaultAttendanceMonth(AttendanceMonth attendanceMonth){
		attendanceMonth.getAttendanceHelper().updateAttendanceHelperStatus(attendanceMonth.getAttendanceStatus());
    	return attendanceMonth;
    }
    
    /**
     * 添加考勤跳转后的年份和月份默认值
     * @author Meng Lingshuai
     */
    public AttendanceMonth getDefaultYearAndMonth(){
    	int defaultYear;
    	int defaultMonth;
    	AttendanceMonth attendanceMonth = new AttendanceMonth();
    	User user = UserUtils.getUser();
    	attendanceMonth.setName(user.getName());
    	List<AttendanceMonth> list = attendanceMonthDao.getAttendance(attendanceMonth);
    	List<Integer> list1 = getStartDateAndEndDate();
    	int startYear = list1.get(0);
    	int startMonth = list1.get(1);
    	int endYear = list1.get(2);
    	int endMonth = list1.get(3);
		int resultYear = 0;
		int resultMonth = 0;
		List<Integer> worklist = new ArrayList<Integer>();
		if(startYear == endYear) {
	    	for(int j = startMonth; j<=endMonth;j++) {
	    		worklist.add(j);
	    		for(AttendanceMonth attendanceMonth1 : list) {
	    			if(j==attendanceMonth1.getMonth()) {
	    				worklist.remove(worklist.size()-1);
	    			}
	    		}
	    	}
	    	if(worklist.size()!=0) {
				resultYear = startYear;
				resultMonth = worklist.get(0);
			}
		}else if(startYear < endYear){
			for(int i = startYear; i <= endYear; i++) {
				if(i == startYear) {
					for(int j = startMonth; j<=12;j++) {
			    		worklist.add(j);
			    		for(AttendanceMonth attendanceMonth1 : list) {
			    			if(i==attendanceMonth1.getYear() && j==attendanceMonth1.getMonth()) {
			    				worklist.remove(worklist.size()-1);
			    			}
			    		}
			    	}
					if(worklist.size()!=0) {
						resultYear = i;
						resultMonth = worklist.get(0);
					}
				}
				if(i > startYear && i < endYear && worklist.size() == 0) {
					for(int j = 1; j<=12;j++) {
			    		worklist.add(j);
			    		for(AttendanceMonth attendanceMonth1 : list) {
			    			if(i==attendanceMonth1.getYear() && j==attendanceMonth1.getMonth()) {
			    				worklist.remove(worklist.size()-1);
			    			}
			    		}
			    	}
					if(worklist.size()!=0) {
						resultYear = i;
						resultMonth = worklist.get(0);
					}
				}
				if(i == endYear && worklist.size() == 0) {
					for(int j = 1; j<=endMonth;j++) {
			    		worklist.add(j);
			    		for(AttendanceMonth attendanceMonth1 : list) {
			    			if(i==attendanceMonth1.getYear() && j==attendanceMonth1.getMonth()) {
			    				worklist.remove(worklist.size()-1);
			    			}
			    		}
			    	}
					if(worklist.size()!=0) {
						resultYear = i;
						resultMonth = worklist.get(0);
					}
				}
			}
		}
    	defaultYear = resultYear;
    	defaultMonth = resultMonth;
    	attendanceMonth.setYear(defaultYear);
    	attendanceMonth.setMonth(defaultMonth);
    	return attendanceMonth;
    }
    
    /**
	 * 获取用户在DB中已经存在的AttendanceMonth
	 * @author Meng Lingshuai
	 */
	public List<AttendanceMonth> getExistAttendanceMonth(){
		AttendanceMonth existAttendanceMonth = new AttendanceMonth();
    	User user = UserUtils.getUser();
    	existAttendanceMonth.setName(user.getName());
    	List<AttendanceMonth> list = attendanceMonthDao.getAttendance(existAttendanceMonth);
		return list;
	}
	
	/**
	 * 获取用户的入职年月和截止年月
	 * @author Meng Lingshuai
	 */
	public List<Integer> getStartDateAndEndDate() {
		User user = UserUtils.getUser();
		Date startDate = user.getEntryDate();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(startDate);
    	int startYear = calendar.get(Calendar.YEAR);
    	int startMonth = calendar.get(Calendar.MONTH)+1;
    	Calendar date = Calendar.getInstance();
    	int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH)+1;
		int endYear;
		int endMonth;
    	if(month==1) {
    		endYear = year-1;
    		endMonth = 12;
		}else {
			endYear = year;
			endMonth = month-1;
		}
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	list.add(startYear);
    	list.add(startMonth);
    	list.add(endYear);
    	list.add(endMonth);
		return list;
	}
    
    /**
     * 获取某个月的考勤状态列表
     * @author Meng Lingshuai
     */
    public List<AttendanceDay> getAttendanceStatusByYearAndMonth(int year, int month){
    	String strDateDay = year + "-" + month;
    	Calendar calendar = Calendar.getInstance(); 
    	Date dateDay = AttendanceUtils.strToDate_yM(strDateDay);
		calendar.setTime(dateDay); 
		int daysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		ArrayList<AttendanceDay> attendanceDayList = new ArrayList<AttendanceDay>();
		for(int i=1; i<=daysCount; i++) {
			String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
			String strDateWeek = year + "-" + month + "-" + i;
			Calendar calendar1 = Calendar.getInstance();
			Date dateWeek = AttendanceUtils.strToDate_yMd(strDateWeek);
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
			attendanceInsert.setLocation("大连市");
			attendanceInsert.setStatus(defaultStatus);
			attendanceDayList.add(attendanceInsert);
		}
    	return attendanceDayList;
    }
    
    /**
	 * 将添加或修改中改变的值赋给atendanceStatus
	 * @author Meng Lingshuai
	 */
    public AttendanceMonth changeValueToAtendanceStatus(AttendanceMonth attendanceMonth, List<AttendanceDay> attendanceStatus) {
    	AttendanceHelper attendanceHelper = attendanceMonth.getAttendanceHelper();
    	int count = attendanceStatus.size();
    	for(int i = 0; i < count; i++) {
    		AttendanceDay attendanceDay = attendanceStatus.get(i);
    		switch(i) {
    		case 0:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_1());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_1());
    			break;
    		case 1:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_2());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_2());
    			break;
    		case 2:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_3());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_3());
    			break;
    		case 3:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_4());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_4());
    			break;
    		case 4:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_5());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_5());
    			break;
    		case 5:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_6());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_6());
    			break;
    		case 6:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_7());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_7());
    			break;
    		case 7:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_8());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_8());
    			break;
    		case 8:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_9());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_9());
    			break;
    		case 9:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_10());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_10());
    			break;
    		case 10:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_11());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_11());
    			break;
    		case 11:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_12());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_12());
    			break;
    		case 12:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_13());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_13());
    			break;
    		case 13:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_14());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_14());
    			break;
    		case 14:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_15());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_15());
    			break;
    		case 15:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_16());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_16());
    			break;
    		case 16:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_17());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_17());
    			break;
    		case 17:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_18());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_18());
    			break;
    		case 18:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_19());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_19());
    			break;
    		case 19:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_20());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_20());
    			break;
    		case 20:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_21());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_21());
    			break;
    		case 21:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_22());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_22());
    			break;
    		case 22:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_23());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_23());
    			break;
    		case 23:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_24());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_24());
    			break;
    		case 24:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_25());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_25());
    			break;
    		case 25:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_26());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_26());
    			break;
    		case 26:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_27());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_27());
    			break;
    		case 27:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_28());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_28());
    			break;
    		case 28:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_29());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_29());
    			break;
    		case 29:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_30());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_30());
    			break;
    		case 30:
    			attendanceDay.setStatus(attendanceHelper.getStatus_day_31());
    			attendanceDay.setLocation(attendanceHelper.getLocation_day_31());
    			break;
    		default:
    			break;	
    		}
    	}
    	attendanceMonth.setAttendanceHelper(null);
    	attendanceMonth.setAttendanceStatus(attendanceStatus);
    	return attendanceMonth;
    }
	
	/**
	 * 查询考勤状态
	 */
	public Page<AttendanceMonth> getAttendanceShowAllPage(AttendanceMonth attendance) {
		int defaultYear;
		int defaultMonth;
		Integer month = attendance.getMonth();
		Integer year = attendance.getYear();
		User user = new User();
		List<User> userList = userDao.findList(user);

		Page<AttendanceMonth> returnPage = attendance.getPage();
		List<AttendanceMonth> returnList = returnPage.getList();

		for (User userInformation : userList) {
			String name = userInformation.getName();
			AttendanceMonth attendanceInsert = new AttendanceMonth();
			if (month == null && year == null) {
				Calendar date = Calendar.getInstance();
				int currentYear = date.get(Calendar.YEAR);
				int currentMonth = date.get(Calendar.MONTH) + 1;
				if (currentMonth == 1) {
					defaultYear = currentYear - 1;
					defaultMonth = 12;
				} else {
					defaultYear = currentYear;
					defaultMonth = currentMonth - 1;
				}
				attendanceInsert.setName(name);
				attendanceInsert.setYear(defaultYear);
				attendanceInsert.setMonth(defaultMonth);
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
		
		returnPage.setCount(returnList.size()); 
		returnPage.setList(returnList);
		
		return returnPage;
	}
	
	/**
	 * 查询考勤:下拉框显示的年和月
	 */
	public AttendanceMonth getAttendanceMonth(AttendanceMonth attendance){		
		int defaultYear;
    	int defaultMonth;
    	Integer month = attendance.getMonth();
		Integer year = attendance.getYear();
    	AttendanceMonth updateAttendanceMonth = new AttendanceMonth();
    	if(month == null && year == null){    //默认显示
    		Calendar date = Calendar.getInstance();
    		int currentYear = date.get(Calendar.YEAR);
    		int currentMonth = date.get(Calendar.MONTH)+1;
    		if(currentMonth==1) {
    			defaultYear = currentYear-1;
    			defaultMonth = 12;
    		}else {  
    			defaultYear = currentYear;
        		defaultMonth = currentMonth-1;
    		}
    		updateAttendanceMonth.setYear(defaultYear);
        	updateAttendanceMonth.setMonth(defaultMonth);
    	}else{ //指定年和月的查询
    		updateAttendanceMonth.setYear(year);
        	updateAttendanceMonth.setMonth(month);
    	}
    	
    	return updateAttendanceMonth;
    	
    }
	
	public List<AttendanceMonth> getAttendanceShow(AttendanceMonth attendance) {
		AttendanceMonth attendanceInsert = new AttendanceMonth();
		String id = attendance.getId();		
		attendanceInsert.setId(id);
		List<AttendanceMonth> attendanceList = attendanceMonthDao
				.getIdAttendance(attendanceInsert);
		return attendanceList;
	}
}
