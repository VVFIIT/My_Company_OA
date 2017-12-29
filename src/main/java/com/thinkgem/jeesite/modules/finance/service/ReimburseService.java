package com.thinkgem.jeesite.modules.finance.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.finance.dao.ReimburseDao;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 报销
 * @author Grace
 * @date 2017年12月25日 下午4:04:52
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ReimburseService {

    @Autowired
    private ReimburseDao reimburseDao;

    /**
     * 报销列表数据
     */
    public Page<ReimburseMain> reimburseMainList(Page<ReimburseMain> page, ReimburseMain reimburseMain) {
        reimburseMain.setPage(page);
        User user = UserUtils.getUser();
        reimburseMain.setApplicantId(user.getId());
        reimburseMain.setOfficeId(user.getOffice().getId());
        if ("1".equals(reimburseMain.getOfficeId()) || "8".equals(reimburseMain.getOfficeId())) {
            List<ReimburseMain> reimburseMainLists = reimburseDao.findAllNameList(reimburseMain);
            page.setList(reimburseMainLists);
        } else {
            List<ReimburseMain> reimburseMainLists = reimburseDao.findOnlyNameList(reimburseMain);
            page.setList(reimburseMainLists);
        }
        return page;
    }

}
