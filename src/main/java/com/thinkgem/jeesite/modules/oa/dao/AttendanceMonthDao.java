package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.modules.oa.entity.Attendance;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
/**
 * Created by GQR on 2017/10/18.
 */

public class AttendanceMonthDao {
    //MongoTemplate是数据库和代码之间的接口，对数据库的操作都在它里面
    @Autowired
    private MongoTemplate mongoTemplate;

    //@Override(暂留 后续可能会使用impl 重新)
    public AttendanceMonth findAll() {
        return this.mongoTemplate.findOne(new Query(Criteria.where("name").is("郭庆荣")), AttendanceMonth.class);
    }

    //添加一个实体

    //根据年月 返回实体

    //update对应实体

    //findAll（XX年XX月）return List<processStatus>

}
