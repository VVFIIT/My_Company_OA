package com.thinkgem.jeesite.modules.finance.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;
import com.thinkgem.jeesite.modules.finance.helper.ReimburseModel;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

/**
 * 报销主表
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:03:29
 * @since 1.0.0
 */
@MyBatisDao
public interface ReimburseMainDao extends CrudDao<ReimburseMain> {

	public List<ReimburseModel> findShowList(ReimburseModel reimburseModel);

	public ReimburseModel getShow(String id);

}
