package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GQR on 2017/10/18.
 */
@Service
public class AttendanceMonthDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 插入考勤
	 */
	public void insert(AttendanceMonth attendanceMonth) {
		this.mongoTemplate.insert(attendanceMonth);

	}

	/*
	 * 查询
	 */
	public List<AttendanceMonth> getAttendance(AttendanceMonth attendanceMonth) {
		Query query = new Query();
		if (StringUtils.isNotBlank(attendanceMonth.getName())) {
			query.addCriteria(Criteria.where("name").regex(".*?\\" + attendanceMonth.getName() + ".*"));
		}
		if (attendanceMonth.getYear() != null) {
			query.addCriteria(Criteria.where("year").is(attendanceMonth.getYear()));
		}
		if (attendanceMonth.getMonth() != null) {
			query.addCriteria(Criteria.where("month").is(attendanceMonth.getMonth()));
		}

		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "year")));
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "month")));
		return this.mongoTemplate.find(query, AttendanceMonth.class);
	}

	/*
	 * 更新考勤
	 */
	public void update(AttendanceMonth attendanceMonth) {
		Query query = new Query(Criteria.where("_id").is(attendanceMonth.getId()));
		Update update = new Update();
		if (attendanceMonth.getAttendanceStatus().size() > 0 && attendanceMonth.getAttendanceStatus() != null) {
			update.set("attendanceStatus", attendanceMonth.getAttendanceStatus());
		}
		if (StringUtils.isNotBlank(attendanceMonth.getDepartment())) {
			update.set("department", attendanceMonth.getDepartment());
		}
		if (attendanceMonth.getMonth() != null) {
			update.set("month", attendanceMonth.getMonth());
		}
		if (attendanceMonth.getYear() != null) {
			update.set("year", attendanceMonth.getYear());
		}
		if (StringUtils.isNotBlank(attendanceMonth.getName())) {
			update.set("name", attendanceMonth.getName());
		}
		if (StringUtils.isNotBlank(attendanceMonth.getProcessStatus())) {
			update.set("processStatus", attendanceMonth.getProcessStatus());
		}
		mongoTemplate.updateMulti(query, update, Attendance.class);
	}

	/**
	 * 查询唯一姓名所有
	 */
	public List<AttendanceMonth> getNameAttendance(AttendanceMonth attendanceMonth) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(attendanceMonth.getName()));
		query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "month")));
		return this.mongoTemplate.find(query, AttendanceMonth.class);
	}
}
