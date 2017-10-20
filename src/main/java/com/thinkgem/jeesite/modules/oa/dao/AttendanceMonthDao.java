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
			query.addCriteria(Criteria.where("year").is(attendanceMonth.getMonth()));
		}

		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "year")));
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "month")));
		return this.mongoTemplate.find(query, AttendanceMonth.class);
	}

	/*
	 * 更新考勤实体
	 */
	public void update(AttendanceMonth attendanceMonth) {
		// Query query = new
		// Query(Criteria.where("id").is(attendanceMonth.getId()));
		Query query = new Query(Criteria.where("name").is("张大天"));
		Update update = new Update();

		// update.pull("id", attendanceMonth.getId());
		// update.pull("attendanceStatus",
		// attendanceMonth.getAttendanceStatus());
		// update.pull("department", attendanceMonth.getDepartment());
		update.pull("month", attendanceMonth.getMonth());
		// update.pull("year", attendanceMonth.getYear());
		// update.pull("processStatus", attendanceMonth.getProcessStatus());

		mongoTemplate.upsert(query, update, Attendance.class);
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
