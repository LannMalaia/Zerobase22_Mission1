package com.mala.wifi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	
	public static String toText(Date date) {
		return sdf.format(date);
	}
	public static String toTextFromNow() {
		return sdf.format(new Date(System.currentTimeMillis()));
	}
	public static Date toDate(String string) {
		try {
			return sdf.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
