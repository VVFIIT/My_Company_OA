package com.thinkgem.jeesite.modules.finance.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finance.entity.ReimburseMain;

/**
 * 报销主表
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:03:29
 * @since 1.0.0
 */
@MyBatisDao
public interface ReimburseMainDao extends CrudDao<ReimburseMain> {

	/**
	 * 查询List
	 * 
	 * @param reimburseModel
	 * @return
	 * @author Grace
	 * @date 2018年1月9日 下午4:27:22
	 */
	public List<ReimburseMain> findShowList(ReimburseMain reimburseMain);


	/**
	 * 根据主键查实体
	 * 
	 * @return
	 * @author Grace
	 * @param mainId 
	 * @date 2018年1月9日 下午4:27:15
	 */
	public ReimburseMain getMainById(String mainId);

}
