package com.springdev.intg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import com.commons.utils.FileUtils;
import com.commons.utils.ObjectUtils;

@SuppressWarnings("unused")
public class TestUtils extends BeanHelper<Object> {

	public static void main(String[] args) {
		deyt(null);
	}

	// worst case O(n)+compares
	private static List<GeoTarget> cleanUpByPriority(List<GeoTarget> tarList, GeoTarget tar) {
		int first_size = tarList.size();
		for (int index = 0; index < first_size; index++) {
			GeoTarget ref = tarList.get(index);
			if (tar.getScore() > ref.getScore()) {
				tarList = new ArrayList<GeoTarget>(0);
				tarList.add(tar);
				return tarList;
			} else if (ref.getScore() == tar.getScore()) {
				tarList.add(tar);
			}
		}
		return tarList;
	}

	private static void deyt(Object x) {
		System.out.println("OBJ");
	}

	private static void deyt(Integer x) {
		System.out.println("INT");
	}
}
