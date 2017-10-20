package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.modules.oa.dao.AttendanceMonthDao;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by GQR on 2017/10/18.
 */
@Service
@Transactional(readOnly = true)
public class AttendanceMonthService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AttendanceMonthDao attendanceMonthDao;

	/**
	 * 根据年月 姓名查询
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<AttendanceMonth> getAttendance() {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		attendanceMonth.setName("张");
		// attendanceMonth.setMonth(11);
		// attendanceMonth.setYear(2017);
		List<AttendanceMonth> attendanceMonthList = attendanceMonthDao.getAttendance(attendanceMonth);
		return attendanceMonthList;
	}

	/**
	 * 插入考勤实体
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insertAttendanceMonth() {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		List<AttendanceDay> attendanceDayList = new ArrayList<AttendanceDay>();
		AttendanceDay attendanceDay = new AttendanceDay();
		attendanceDay.setLocation("大连");
		attendanceDay.setDate(2);
		attendanceDay.setStatus("公休日");
		attendanceDay.setWeek("周六");

		attendanceDayList.add(attendanceDay);

		attendanceMonth.setAttendanceStatus(attendanceDayList);
		attendanceMonth.setId(UUID.randomUUID().toString());
		attendanceMonth.setDepartment("研发一部");
		attendanceMonth.setMonth(11);
		attendanceMonth.setName("张大天");
		attendanceMonth.setProcessStatus("2");
		attendanceMonth.setYear(2017);

		attendanceMonthDao.insert(attendanceMonth);
	}

	/**
	 * 更新
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void update() {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		List<AttendanceDay> attendanceDayList = new ArrayList<AttendanceDay>();
		AttendanceDay attendanceDay = new AttendanceDay();
		attendanceDay.setLocation("南海");
		attendanceDay.setDate(3);
		attendanceDay.setStatus("工作日");
		attendanceDay.setWeek("周二");

		attendanceDayList.add(attendanceDay);

		attendanceMonth.setAttendanceStatus(attendanceDayList);
		attendanceMonth.setId("9017cd60-5c7c-450c-b710-f98aad2dd756");
		attendanceMonth.setDepartment("研发5部");
		attendanceMonth.setMonth(5);
		attendanceMonth.setName("如来");
		attendanceMonth.setProcessStatus("3");
		attendanceMonth.setYear(2014);

		attendanceMonthDao.update(attendanceMonth);
	}

	/**
	 * 查询所有姓名唯一的信息
	 */
	public List<AttendanceMonth> getAllAttendance() {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		attendanceMonth.setName("姜吉庆");
		List<AttendanceMonth> attendanceMonthList = attendanceMonthDao.getNameAttendance(attendanceMonth);
		return attendanceMonthList;
	}

//	/**
//	 * 获取考勤记录
//	 * @param attendanceMonth
//	 * @return
//	 */
//	public AttendanceMonth getRecordList(AttendanceMonth attendanceMonth) {
//		attendanceMonth.setOaNotifyRecordList(attendanceMonthDao.findList(new OaNotifyRecord(attendanceMonth)));
//		return attendanceMonth;
//	}

}
