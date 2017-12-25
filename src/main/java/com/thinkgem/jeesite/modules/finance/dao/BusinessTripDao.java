/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.finance.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.finance.entity.BusinesstripApplication;

/**
 * 报销
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:03:47
 * @since 1.0.0
 */
@MyBatisDao
public interface BusinessTripDao extends CrudDao<BusinesstripApplication> {
	
	
}
