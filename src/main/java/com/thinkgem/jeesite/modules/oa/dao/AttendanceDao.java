package com.thinkgem.jeesite.modules.oa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.oa.entity.Attendance;

/**
 * 考勤AttendanceDao
 */
@Service
public class AttendanceDao {

    //MongoTemplate是数据库和代码之间的接口，对数据库的操作都在它里面
    @Autowired
    private MongoTemplate mongoTemplate;

    //@Override(暂留 后续可能会使用impl 重新)
    public Attendance findAll() {
        return this.mongoTemplate.findOne(new Query(Criteria.where("name").is("gqr")), Attendance.class);
    }
}