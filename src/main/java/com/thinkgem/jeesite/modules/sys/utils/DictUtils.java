/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.dao.DictDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

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
	
	public static String getDefaultStatus(int index){
		String status = "";
		switch(index) {
		case 1:
			status = "status_day_1";
			break;
		case 2:
			status = "status_day_2";
			break;
		case 3:
			status = "status_day_3";
			break;
		case 4:
			status = "status_day_4";
			break;
		case 5:
			status = "status_day_5";
			break;
		case 6:
			status = "status_day_6";
			break;
		case 7:
			status = "status_day_7";
			break;
		case 8:
			status = "status_day_8";
			break;
		case 9:
			status = "status_day_9";
			break;
		case 10:
			status = "status_day_10";
			break;
		case 11:
			status = "status_day_11";
			break;
		case 12:
			status = "status_day_12";
			break;
		case 13:
			status = "status_day_13";
			break;
		case 14:
			status = "status_day_14";
			break;
		case 15:
			status = "status_day_15";
			break;
		case 16:
			status = "status_day_16";
			break;
		case 17:
			status = "status_day_17";
			break;
		case 18:
			status = "status_day_18";
			break;
		case 19:
			status = "status_day_19";
			break;
		case 20:
			status = "status_day_20";
			break;
		case 21:
			status = "status_day_21";
			break;
		case 22:
			status = "status_day_22";
			break;
		case 23:
			status = "status_day_23";
			break;
		case 24:
			status = "status_day_24";
			break;
		case 25:
			status = "status_day_25";
			break;
		case 26:
			status = "status_day_26";
			break;
		case 27:
			status = "status_day_27";
			break;
		case 28:
			status = "status_day_28";
			break;
		case 29:
			status = "status_day_29";
			break;
		case 30:
			status = "status_day_30";
			break;
		case 31:
			status = "status_day_31";
			break;
		default:
			break;
		}
		status = "attendanceHelper."+status;
		return status;
	}
	
	public static String getDefaultLocation(int index){
		String location = "";
		switch(index) {
		case 1:
			location = "location_day_1";
			break;
		case 2:
			location = "location_day_2";
			break;
		case 3:
			location = "location_day_3";
			break;
		case 4:
			location = "location_day_4";
			break;
		case 5:
			location = "location_day_5";
			break;
		case 6:
			location = "location_day_6";
			break;
		case 7:
			location = "location_day_7";
			break;
		case 8:
			location = "location_day_8";
			break;
		case 9:
			location = "location_day_9";
			break;
		case 10:
			location = "location_day_10";
			break;
		case 11:
			location = "location_day_11";
			break;
		case 12:
			location = "location_day_12";
			break;
		case 13:
			location = "location_day_13";
			break;
		case 14:
			location = "location_day_14";
			break;
		case 15:
			location = "location_day_15";
			break;
		case 16:
			location = "location_day_16";
			break;
		case 17:
			location = "location_day_17";
			break;
		case 18:
			location = "location_day_18";
			break;
		case 19:
			location = "location_day_19";
			break;
		case 20:
			location = "location_day_20";
			break;
		case 21:
			location = "location_day_21";
			break;
		case 22:
			location = "location_day_22";
			break;
		case 23:
			location = "location_day_23";
			break;
		case 24:
			location = "location_day_24";
			break;
		case 25:
			location = "location_day_25";
			break;
		case 26:
			location = "location_day_26";
			break;
		case 27:
			location = "location_day_27";
			break;
		case 28:
			location = "location_day_28";
			break;
		case 29:
			location = "location_day_29";
			break;
		case 30:
			location = "location_day_30";
			break;
		case 31:
			location = "location_day_31";
			break;
		default:
			break;
		}
		location = "attendanceHelper."+location;
		return location;
	}

	//(如果是提交状态则隐藏修改按钮)
	public static String getCheckStatus(String processStatus) {
		String status = "";
		if ("2".equals(processStatus)||"3".equals(processStatus)||"5".equals(processStatus)) {
			status = "none;";
		} else {
			status = "inline";
		}
		status = "display:" + status;
		return status;
	}
	
	//审批完成态显示 导出按钮
	public static String getCheckStatusShow(String processStatus) {
		String status = "";
		if ("5".equals(processStatus)) {
			status = "inline";
		} else {
			status = "none;";
		}
		status = "display:" + status;
		return status;
	}
	
	//审批完成态显示 导出按钮
	public static String getCheckStatusShowOn(String processStatus) {
		String status = "";
		if ("2".equals(processStatus)||"3".equals(processStatus)||"4".equals(processStatus)||"5".equals(processStatus)) {
			status = "inline";
		} else {
			status = "none;";
		}
		status = "display:" + status;
		return status;
	}
	
}
