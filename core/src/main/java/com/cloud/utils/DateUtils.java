package com.cloud.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cloud.log.LogShow;

public class DateUtils {

	private String yyyy;
	private String mm;
	private String dd;

	public static Date getDateFromString(String stringDate) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try {
			date = df.parse(stringDate);
		} catch (Exception e) {
			LogShow.getLogDebug(e.getMessage());
			return null;
		}
		return date;
	}

	public static String dayToString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String dayStr = "";
		try {
			dayStr = df.format(date);
		} catch (Exception e) {
			LogShow.getLogDebug(e.getMessage());
		}
		return dayStr;
	}

	public static String timeToString(Date date) {
		DateFormat formatterTime = new SimpleDateFormat("HH:mm");
		String timeStr = "";
		try {
			timeStr = formatterTime.format(date);
		} catch (Exception e) {
			LogShow.getLogDebug(e.getMessage());
		}
		return timeStr;
	}

	public static Date getToday() {

		Date today = new Date();

		return today;
	}

	public static Date getNext24() {

		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);

		cal.add(Calendar.HOUR_OF_DAY, 24);

		Date next24 = cal.getTime();
		return next24;

	}

	public static String getDateFromKey(String dateKey){
		String ret = "";
		if("1".equals(dateKey)){
			ret = DateUtils.dayToString(DateUtils.getNext24());
		}else{
			ret = DateUtils.dayToString(DateUtils.getToday());
		}
		return ret;
	}
	
	public String getYyyy() {
		return yyyy;
	}

	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
					.get(Calendar.DAY_OF_YEAR) == cal2
				.get(Calendar.DAY_OF_YEAR));
	}

	public static boolean isToday(Date date) {
		return isSameDay(date, Calendar.getInstance().getTime());
	}

	public static boolean isToday(Calendar cal) {
		return isSameDay(cal, Calendar.getInstance());
	}

	public static boolean isWithinDaysFuture(Date date, int days) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return isWithinDaysFuture(cal, days);
	}

	public static boolean isWithinDaysFuture(Calendar cal, int days) {
		if (cal == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar today = Calendar.getInstance();
		Calendar future = Calendar.getInstance();
		future.add(Calendar.DAY_OF_YEAR, days);
		return (isAfterDay(cal, today) && !isAfterDay(cal, future));
	}

	public static boolean isAfterDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isAfterDay(cal1, cal2);
	}

	public static boolean isAfterDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The dates must not be null");
		}
		if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA))
			return false;
		if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA))
			return true;
		if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR))
			return false;
		if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR))
			return true;
		return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
	}
}
