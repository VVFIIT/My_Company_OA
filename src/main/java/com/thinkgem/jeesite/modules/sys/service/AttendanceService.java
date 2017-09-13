package com.thinkgem.jeesite.modules.sys.service;

import com.thinkgem.jeesite.modules.sys.dao.AttendanceDao;
import com.thinkgem.jeesite.modules.sys.entity.Attendance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.String;
import java.lang.reflect.Field;
import java.util.Arrays;

import javax.annotation.Resource;
import java.util.List;

/**
 * 考勤Service
 */
@Service
@Transactional(readOnly = true)
public class AttendanceService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AttendanceDao attendanceDao;

    @Transactional(readOnly = false)
    public void getName() {
        attendanceDao.findAll();
        logger.debug("@@@@@@@@@@@@@@@@@");
        logger.debug(attendanceDao.findAll().getId());
        logger.debug("@@@@@@@@@@@@@@@@@");
    }
}
