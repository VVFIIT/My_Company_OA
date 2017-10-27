package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.oa.dao.SpecialDayDao;
import com.thinkgem.jeesite.modules.oa.entity.SpecialDay;

/**
 * Created by Grace on 2017/10/27.
 */
@Service
@Transactional(readOnly = true)
public class SpecialDayService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private SpecialDayDao specialDayDao;

	/**
	 * 根据类型查list
	 * 
	 * @param specialDay
	 * @return
	 */
	public List<SpecialDay> findByType(SpecialDay specialDay) {
		List<SpecialDay> list=specialDayDao.findByType(specialDay);
		return list;
	}

}
