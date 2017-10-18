package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * 考勤AttendanceDao
 */
@Service
public class AttendanceDao {

    // MongoTemplate是数据库和代码之间的接口，对数据库的操作都在它里面
    @Autowired
    private MongoTemplate mongoTemplate;

    // @Override(暂留 后续可能会使用impl 重新)
    public Attendance findAll() {
        return this.mongoTemplate.findOne(new Query(Criteria.where("name").is("6666666666666")), Attendance.class);
    }

    /**
     * 根据姓名查询考勤List
     */
    public List<Attendance> getAttendance(Attendance attendance) {
        // 模糊查询
        Query query = new Query(Criteria.where("name").regex(".*?\\" + attendance.getName() + ".*"));
        return this.mongoTemplate.find(query, Attendance.class);
    }

    /**
     * 插入考勤实体
     */
    public void insert(Attendance attendance) {
        this.mongoTemplate.insert(attendance);
    }

    /**
     * 查询所有
     */
    public List<Attendance> getAllAttendance(Attendance attendance) {
        return this.mongoTemplate.findAll(Attendance.class);
    }

    /*
	 * 更新考勤实体
	 */
	public void update(Attendance attendance) {
		Query query = new Query(Criteria.where("name").is(attendance.getName()));
		Update update = new Update();
		update.pull("attStatus", "60");
		mongoTemplate.upsert(query, update, Attendance.class);
	}
	
	/*
	 * 查询根据时间(wait)
	 */
	public List<Attendance> getAttendanceByDate(Attendance attendance) {
		Criteria criatira = new Criteria();
//		criatira.andOperator(Criteria.where("year").is(attendance.getYear()), Criteria.where("month").is(attendance.getMonth()));
		criatira.andOperator(Criteria.where("year").is(attendance.getYear()).and("month").is(attendance.getMonth()));
		mongoTemplate.find(new Query(criatira), Attendance.class);
		return this.mongoTemplate.findAll(Attendance.class);	
	}
	
	/*
	 * 删除
	 */
	public void delete(Attendance attendance) {
		this.mongoTemplate.remove(attendance);
	}
}