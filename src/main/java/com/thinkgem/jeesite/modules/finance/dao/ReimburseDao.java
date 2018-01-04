package com.thinkgem.jeesite.modules.finance.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;

import java.util.List;

/**
 * 报销
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:03:29
 * @since 1.0.0
 */
@MyBatisDao
public interface ReimburseDao extends CrudDao<ReimburseMain> {

    /**
     * 通过applicantId获取个人报销列表
     * @param reimburseMain
     * @return
     */
    public List<ReimburseMain> findOnlyNameList(ReimburseMain reimburseMain);

    /**
     * 通过OfficeId获取全部人报销列表
     * @param reimburseMain
     * @return
     */
    public List<ReimburseMain> findAllNameList(ReimburseMain reimburseMain);

    /**
     * 通过Id获取指定人报销详情
     * @param string
     * @return
     */
    public ReimburseMain findOnly(String string);

}
