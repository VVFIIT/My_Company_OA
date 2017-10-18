package com.thinkgem.jeesite.modules.oa.service;

import com.thinkgem.jeesite.modules.oa.dao.AttendanceMonthDao;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by GQR on 2017/10/18.
 */
@Service
@Transactional(readOnly = true)
public class AttendanceMonthService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AttendanceMonthDao attendanceMonthDao;

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public AttendanceMonth getName() {
        logger.debug("@@@@@@@@@@@@@@@@@");
        return attendanceMonthDao.findAll();
    }
}
