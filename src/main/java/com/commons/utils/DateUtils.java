package com.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static Date parseDate(String inputDate,String dateFormat)throws Exception{
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		final Date formattedDate = sdf.parse(inputDate);
		return formattedDate;
	}
}
