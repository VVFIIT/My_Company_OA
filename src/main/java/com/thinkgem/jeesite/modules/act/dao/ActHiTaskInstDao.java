/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.act.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.act.entity.Act;

/**
 * ActHiTaskInst
 * 
 * @author Grace
 * @date 2017年11月20日 下午4:05:04
 * @since 1.0.0
 */
@MyBatisDao
public interface ActHiTaskInstDao extends CrudDao<Act> {

	/**
	 * 根据procinstId查taskId
	 * 
	 * @param procInsId
	 * @return
	 * @author Grace
	 * @date 2017年11月20日 下午5:43:10
	 */
	public Act findIdByProcInsId(String procInsId);

	/**
	 * 根据procinstId,key 查taskId
	 * 
	 * @param act
	 * @return
	 * @author Grace
	 * @date 2018年1月10日 下午2:20:28
	 */
	public Act findIdByProcInsIdAndActId(Act act);


}
