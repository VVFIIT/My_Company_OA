package com.thinkgem.jeesite.modules.finance.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseHospitalityDao;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseHospitality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author jjq
 * @Create 2018-01-03-11:04
 **/
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ReimburseHospitalityService {

    @Autowired
    private ReimburseHospitalityDao reimburseHospitalityDao;


    /**
     * 招待费详情
     */
    public Page<ReimburseHospitality> reimburseHospitalityInformation(Page<ReimburseHospitality> page, String id) {
        ReimburseHospitality reimburseHospitality = new ReimburseHospitality();
        reimburseHospitality.setPage(page);
        List<ReimburseHospitality> reimburseHospitalitys = reimburseHospitalityDao.findList(id);
        page.setList(reimburseHospitalitys);
        return page;
    }
}
