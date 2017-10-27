package com.thinkgem.jeesite.modules.oa.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Menglingshuai on 2017/10/27.
 */
public class AttendanceUtils {
	
	/*
	 * 将字符串"yyyy-MM"转化成Date型
	 * @author Meng Lingshuai
	 */
	public static Date strToDate_yM(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date dateDay = null;
		try {
			dateDay = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateDay;
	}
	
	/*
	 * 将字符串"yyyy-MM-dd"转化成Date型
	 * @author Meng Lingshuai
	 */
	public static Date strToDate_yMd(String date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDay = null;
		try {
			dateDay = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateDay;
	}
}
