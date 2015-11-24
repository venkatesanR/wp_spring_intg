package com.commons.utils;

public class StrUtils {
	public static boolean equals(String val1, String val2) {
		return (val1 != null && val2 != null && val1.equals(val2)) ? true : false;
	}

	public static boolean contains(String val1, String val2) {
		return (val1 != null && val2 != null && val1.contains(val2)) ? true : false;
	}
}
