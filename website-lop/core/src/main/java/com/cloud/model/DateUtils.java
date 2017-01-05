package com.cloud.model;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
		
	public static Date getDate(int isToday){
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);  
		cal.add(Calendar.DAY_OF_YEAR, 1); 
		Date tomorrow = cal.getTime();  
		return (isToday==0)?now:tomorrow;
	}

}
