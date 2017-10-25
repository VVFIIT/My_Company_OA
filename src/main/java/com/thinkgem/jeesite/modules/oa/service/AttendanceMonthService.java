package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.oa.dao.AttendanceMonthDao;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDay;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceDayStatus;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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

	private final AttendanceMonthDao attendanceMonthDao;

	@Autowired
	public AttendanceMonthService(AttendanceMonthDao attendanceMonthDao) {
		this.attendanceMonthDao = attendanceMonthDao;
	}

	/**
	 * 根据年月 姓名查询
	 */
	@Transactional(rollbackFor = Exception.class)
	public List<AttendanceMonth> getAttendance(AttendanceMonth attendanceMonth) {
		return attendanceMonthDao.getAttendance(attendanceMonth);
	}

	/**
	 * 插入考勤实体
	 */
	@Transactional(rollbackFor = Exception.class)
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
	@Transactional(rollbackFor = Exception.class)
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
		User user = UserUtils.getUser();
		attendanceMonth.setName(user.getName());
		return attendanceMonthDao.getNameAttendance(attendanceMonth);
	}

	/**
	 * 更新提交状态属性
	 */
	@Transactional(rollbackFor = Exception.class)
	public AttendanceMonth updateProcessStatus(String id) {
		AttendanceMonth attendanceMonth=new AttendanceMonth();
		attendanceMonth.setId(id);
		//修改为提交状态
		attendanceMonth.setProcessStatus("3");
		attendanceMonthDao.update(attendanceMonth);
		return attendanceMonth;
	}
	// /**
	// * 获取考勤记录
	// * @param attendanceMonth
	// * @return
	// */
	// public AttendanceMonth getRecordList(AttendanceMonth attendanceMonth) {
	// attendanceMonth.setOaNotifyRecordList(attendanceMonthDao.findList(new
	// OaNotifyRecord(attendanceMonth)));
	// return attendanceMonth;
	// }

	/**
	 * 计算考勤状态和
	 */
	public List<AttendanceDayStatus> getDayStatusSum() {
		AttendanceMonth attendanceMonth = new AttendanceMonth();
		User user = UserUtils.getUser();
		attendanceMonth.setName(user.getName());
		Page<AttendanceMonth> page = attendanceMonthDao.getAttendancePage(attendanceMonth);
		List<AttendanceMonth> lists = page.getList();
		List<AttendanceDayStatus> list = new ArrayList<AttendanceDayStatus>();
		for (AttendanceMonth list1 : lists) {
			AttendanceDayStatus attendanceDayStatus = new AttendanceDayStatus();
			// 正常出勤,出差-短期,出差-长期,加班,请假,其它带薪假,病假,--公休日,--法定节假日
			int q = 0, w = 0, a = 0, b = 0, c = 0, d = 0, e = 0;
			for (int j = 0; j < list1.getAttendanceStatus().size(); j++) {
				if ("1".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					q++;
					attendanceDayStatus.setNormalDay(q);
				} else if ("2".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					w++;
					attendanceDayStatus.setTravelDayShort(w);
				} else if ("3".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					a++;
					attendanceDayStatus.setTravelDayLong(a);
				} else if ("4".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					b++;
					attendanceDayStatus.setOvertimeDay(b);
				} else if ("5".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					c++;
					attendanceDayStatus.setLeaveDay(c);
				} else if ("6".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					d++;
					attendanceDayStatus.setPaidLeaveDay(d);
				} else if ("7".equals(list1.getAttendanceStatus().get(j).getStatus())) {
					e++;
					attendanceDayStatus.setSickLeaveDay(e);
				}
			}
			list.add(attendanceDayStatus);
		}
//		for (AttendanceMonth anAttendanceMonthList : attendanceMonthList) {

//		}
		return list;
	}

	/**
	 * 我的 考勤查询分页
	 * @param page
	 * @return
	 */
	public Page<AttendanceMonth> page(Page<AttendanceMonth> page) {
		AttendanceMonth attendanceMonth=new AttendanceMonth();
		attendanceMonth.setPage(page);
		return attendanceMonthDao.getAttendancePage(attendanceMonth);	 
	}

}
