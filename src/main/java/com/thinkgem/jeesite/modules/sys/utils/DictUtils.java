/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.dao.DictDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	
	public static String getStatus(int index){
		String status = null;
		switch(index) {
		case 0:
			status = "status_day_1";
		case 1:
			status = "status_day_2";
		case 2:
			status = "status_day_3";
		case 3:
			status = "status_day_4";
		case 4:
			status = "status_day_5";
		case 5:
			status = "status_day_6";
		case 6:
			status = "status_day_7";
		case 7:
			status = "status_day_8";
		case 8:
			status = "status_day_9";
		case 9:
			status = "status_day_10";
		case 10:
			status = "status_day_11";
		case 11:
			status = "status_day_12";
		case 12:
			status = "status_day_13";
		case 13:
			status = "status_day_14";
		case 14:
			status = "status_day_15";
		case 15:
			status = "status_day_16";
		case 16:
			status = "status_day_17";
		case 17:
			status = "status_day_18";
		case 18:
			status = "status_day_19";
		case 19:
			status = "status_day_20";
		case 20:
			status = "status_day_21";
		case 21:
			status = "status_day_22";
		case 22:
			status = "status_day_23";
		case 23:
			status = "status_day_24";
		case 24:
			status = "status_day_25";
		case 25:
			status = "status_day_26";
		case 26:
			status = "status_day_27";
		case 27:
			status = "status_day_28";
		case 28:
			status = "status_day_29";
		case 29:
			status = "status_day_30";
		case 30:
			status = "status_day_31";
		}
		return "attendanceHelper."+status;
	}
	
}
