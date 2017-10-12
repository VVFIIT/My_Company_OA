package com.thinkgem.jeesite.modules.oa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.oa.dao.AttendanceDao;

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
