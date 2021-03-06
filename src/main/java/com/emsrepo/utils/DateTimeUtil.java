package com.emsrepo.utils;

import java.text.ParseException;
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

	// +++++++++++++++
	public static String getNowadayTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static Date toDate(String dateString) {
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
	
		try {
			if (dateString.contains(":")) {
				return formatter1.parse(dateString);
			} else {
				return formatter2.parse(dateString);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
