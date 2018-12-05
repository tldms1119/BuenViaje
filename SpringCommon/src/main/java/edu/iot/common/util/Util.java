package edu.iot.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getToday() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		
		return today;
	}
}
