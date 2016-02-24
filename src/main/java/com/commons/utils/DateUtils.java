package com.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static void main(String args[]) {
		String format = "MMM dd,yyyy";
		System.out.println(addDMYInCalender(-4, Calendar.MONTH, format, new Date()));
	}
	
	public static Date parseDate(String inputDate, String dateFormat) throws Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		final Date formattedDate = sdf.parse(inputDate);
		return formattedDate;
	}

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
	public static String addDMYInCalender(int toBeAdd, int dMY, String format, Date startDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		if (startDate != null) {
			cal.setTime(startDate);
		} else {
			cal.setTime(new Date());
		}

		if (Calendar.DATE == dMY) {
			cal.add(Calendar.DATE, toBeAdd);
		} else if (Calendar.MONTH == dMY) {
			cal.add(Calendar.MONTH, toBeAdd);
		} else if (Calendar.YEAR == dMY) {
			cal.add(Calendar.YEAR, toBeAdd);
		}
		return sdf.format(cal.getTime());
	}
}
