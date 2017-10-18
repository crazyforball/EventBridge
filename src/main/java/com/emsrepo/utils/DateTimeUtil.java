package com.emsrepo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	public static String getNowadayMillsTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		Date d = new Date();
		String str = sdf.format(d);
		return str;
	}
	
	public static String getAfterXdaysMillsTime(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		Date finalDate = calendar.getTime();
		return sdf.format(finalDate);
	}
	
}
