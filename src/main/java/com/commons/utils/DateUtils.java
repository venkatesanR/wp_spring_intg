package com.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	/**
	 * This method used to add date/month/year from given date please use
	 * argument @2:(use Calendar.DATE || Calendar.MONTH ||
	 * Calendar.YEAR) @4:Date to be started to add
	 * 
	 * @param toBeAdd
	 * @param dMY
	 * @param format
	 * @param startDate
	 * @return
	 */
	public static Object addDMYInCalender(int toBeAdd, int dMY, String format, Date startDate, boolean isFormatReqd) {
		Calendar cal = Calendar.getInstance();
		if (startDate != null) {
			cal.setTime(startDate);
		} else {
			cal.setTime(new Date());
		}
		cal = getComputedDate(cal, dMY, toBeAdd);
		if (isFormatReqd && format != null && !format.isEmpty()) {
			return getFormatedDateAsString(cal.getTime(), format);
		}
		return cal.getTime();
	}
	
	public static String getFormatedDateAsString(Date date, String format) {
		String formatedDate = null;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			formatedDate = sdf.format(date);
		}
		return formatedDate;
	}
	
	private static Calendar getComputedDate(Calendar cal, int dMY, int toBeAdd) {
		if (Calendar.DATE == dMY) {
			cal.add(Calendar.DATE, toBeAdd);
		} else if (Calendar.MONTH == dMY) {
			cal.add(Calendar.MONTH, toBeAdd);
		} else if (Calendar.YEAR == dMY) {
			cal.add(Calendar.YEAR, toBeAdd);
		}
		return cal;
	}

}
