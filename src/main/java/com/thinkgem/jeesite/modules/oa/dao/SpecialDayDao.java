package com.thinkgem.jeesite.modules.oa.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.SpecialDay;

/**
 * 特殊日子
 */
@MyBatisDao
public interface SpecialDayDao extends CrudDao<SpecialDay> {
	/**
	 * 根据类型查List
	 * 
	 * @param specialDay
	 * @return
	 */
	public List<SpecialDay> findByType(SpecialDay specialDay);

}