package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;

/**
 * Created by GQR on 2017/10/18.
 */
@Service
public class AttendanceMonthDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 插入考勤
	 * 
	 * @param attendanceMonth
	 * @author Grace
	 * @date 2017年10月23日 下午5:19:25
	 */
	public void insert(AttendanceMonth attendanceMonth) {
		this.mongoTemplate.insert(attendanceMonth);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param attendanceMonth
	 * @return
	 * @author Grace
	 * @date 2017年10月23日 下午5:18:58
	 */
	public List<AttendanceMonth> getAttendance(AttendanceMonth attendanceMonth) {
		Query query = new Query();
		if (StringUtils.isNotBlank(attendanceMonth.getId())) {
			query.addCriteria(Criteria.where("_id").is(attendanceMonth.getId()));
		}
		if (StringUtils.isNotBlank(attendanceMonth.getName())) {
			query.addCriteria(Criteria.where("name").regex(attendanceMonth.getName()));
		}
		if (attendanceMonth.getYear() != null) {
			query.addCriteria(Criteria.where("year").is(attendanceMonth.getYear()));
		}
		if (attendanceMonth.getMonth() != null) {
			query.addCriteria(Criteria.where("month").is(attendanceMonth.getMonth()));
		}
		if (attendanceMonth.getProcessStatus() != null) {
			query.addCriteria(Criteria.where("processStatus").is(attendanceMonth.getProcessStatus()));
		}
		if (attendanceMonth.getProcInsId() != null) {
			query.addCriteria(Criteria.where("procInsId").is(attendanceMonth.getProcInsId()));
		}
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "year")));
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "month")));
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "processStatus")));
		return this.mongoTemplate.find(query, AttendanceMonth.class);
	}

	/*
	 * 查询
	 */
	public List<AttendanceMonth> getAttendanceByName(AttendanceMonth attendanceMonth) {
		Query query = new Query();

		if (StringUtils.isNotBlank(attendanceMonth.getName())) {
			query.addCriteria(Criteria.where("name").regex(attendanceMonth.getName()));
		}
		return this.mongoTemplate.find(query, AttendanceMonth.class);
	}

	/**
	 * 更新考勤
	 * 
	 * @param attendanceMonth
	 * @author Grace
	 * @date 2017年11月6日 下午5:20:25
	 */
	public void update(AttendanceMonth attendanceMonth) {
		Query query = new Query(Criteria.where("_id").is(attendanceMonth.getId()));
		Update update = new Update();
		if (attendanceMonth.getAttendanceStatus() != null) {
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
		if (StringUtils.isNotBlank(attendanceMonth.getProcInsId())) {
			update.set("procInsId", attendanceMonth.getProcInsId());
		}
		if (StringUtils.isNotBlank(attendanceMonth.getHRComment())) {
			update.set("HRComment", attendanceMonth.getHRComment());
		}
		if (StringUtils.isNotBlank(attendanceMonth.getPMComment())) {
			update.set("PMComment", attendanceMonth.getPMComment());
		}
		mongoTemplate.updateMulti(query, update, AttendanceMonth.class);
	}

	/**
	 * 查询唯一姓名所有
	 */
	public List<AttendanceMonth> getNameAttendance(AttendanceMonth attendanceMonth) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(attendanceMonth.getName()));
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "month")));
		return this.mongoTemplate.find(query, AttendanceMonth.class);
	}

	/**
	 * 根据id查询
	 */
	public List<AttendanceMonth> getIdAttendance(AttendanceMonth attendanceMonth) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(attendanceMonth.getId()));
		return this.mongoTemplate.find(query, AttendanceMonth.class);
	}

	/**
	 * 查询考勤分页
	 * 
	 * @param attendanceMonth
	 * @return
	 * @author Grace
	 * @date 2017年10月23日 下午5:20:46
	 */
	public Page<AttendanceMonth> getAttendancePage(AttendanceMonth attendanceMonth) {
		Query query = new Query();
		if (StringUtils.isNotBlank(attendanceMonth.getId())) {
			query.addCriteria(Criteria.where("_id").is(attendanceMonth.getId()));
		}
		if (StringUtils.isNotBlank(attendanceMonth.getName())) {
			query.addCriteria(Criteria.where("name").regex(attendanceMonth.getName()));
		}
		if (attendanceMonth.getYear() != null) {
			query.addCriteria(Criteria.where("year").is(attendanceMonth.getYear()));
		}
		if (attendanceMonth.getMonth() != null) {
			query.addCriteria(Criteria.where("month").is(attendanceMonth.getMonth()));
		}
		if (attendanceMonth.getProcessStatus() != null && !"0".equals(attendanceMonth.getProcessStatus())
				&& !"4".equals(attendanceMonth.getProcessStatus())) {
			query.addCriteria(Criteria.where("processStatus").is(attendanceMonth.getProcessStatus()));
		}
		Long countAll = this.mongoTemplate.count(query, AttendanceMonth.class);
		query.with(new Sort(Direction.DESC, "year", "month"));
		query.skip((attendanceMonth.getPage().getPageNo() - 1) * attendanceMonth.getPage().getPageSize())
				.limit(attendanceMonth.getPage().getPageSize());
		List<AttendanceMonth> list = this.mongoTemplate.find(query, AttendanceMonth.class);
		Page<AttendanceMonth> page = attendanceMonth.getPage();
		page.setCount(countAll);
		page.setList(list);
		return page;
	}

	/**
	 * 返回一个考勤实体
	 *
	 * @param attendanceMonth
	 * @return
	 */
	public AttendanceMonth getAttendanceEntity(AttendanceMonth attendanceMonth) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(attendanceMonth.getId()));
		attendanceMonth = this.mongoTemplate.findOne(query, AttendanceMonth.class);
		return attendanceMonth;
	}

	/**
	 * 根据ProcInsId更新AttanceMonth
	 * 
	 * @param attendanceMonth
	 * @author Grace
	 * @date 2017年11月16日 上午11:38:30
	 */
	public AttendanceMonth updateAttanceMonthByProcInsId(AttendanceMonth attendanceMonth) {
		Query query = new Query(Criteria.where("procInsId").is(attendanceMonth.getProcInsId()));
		AttendanceMonth attendanceMonthOld = mongoTemplate.findAndRemove(query, AttendanceMonth.class);

		if (StringUtils.isNotBlank(attendanceMonth.getProcessStatus())) {
			attendanceMonthOld.setProcessStatus(attendanceMonth.getProcessStatus());
		}

		if (StringUtils.isNotBlank(attendanceMonth.getHRComment())) {
			attendanceMonthOld.setHRComment(attendanceMonth.getHRComment());
		}
		if (StringUtils.isNotBlank(attendanceMonth.getPMComment())) {
			attendanceMonthOld.setPMComment(attendanceMonth.getPMComment());
		}

		mongoTemplate.insert(attendanceMonthOld);
		return attendanceMonthOld;
	}
}
