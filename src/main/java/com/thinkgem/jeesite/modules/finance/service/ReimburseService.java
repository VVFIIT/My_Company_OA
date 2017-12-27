package com.thinkgem.jeesite.modules.finance.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseDao;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.oa.entity.AttendanceMonth;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

/**
 * 报销
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:04:52
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ReimburseService {

    @Autowired
    private ReimburseDao reimburseDao;

    public Page<ReimburseMain> reimburseMainList(Page<ReimburseMain> page) {
        ReimburseMain reimburseMain = new ReimburseMain();
        reimburseMain.setPage(page);
//        reimburseMain.setOfficeId();
//        User user = UserUtils.getUser();
        if("2".equals(reimburseMain.getOfficeId()) || "8".equals(reimburseMain.getOfficeId())){
            List<ReimburseMain> reimburseMainLists = reimburseDao.findAllNameList(reimburseMain);
        } else {
            List<ReimburseMain> reimburseMainLists = reimburseDao.findOnlyNameList(reimburseMain);
        }

        return page;
    }
	
}
