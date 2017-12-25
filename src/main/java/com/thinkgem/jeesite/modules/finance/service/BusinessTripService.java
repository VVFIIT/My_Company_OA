package com.thinkgem.jeesite.modules.finance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 出差
 * 
 * @author Grace
 * @date 2017年12月25日 下午4:04:37
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BusinessTripService {

	
}
