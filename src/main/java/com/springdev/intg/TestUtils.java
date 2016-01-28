package com.springdev.intg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

import com.commons.utils.FileUtils;
import com.commons.utils.ObjectUtils;

public class TestUtils extends BeanHelper<Object> {
	private static final String alphaSeriesTxt = "BXGLWANRPQ";
	static BigDecimal revenue = new BigDecimal(.004);
	static BigDecimal impressions = new BigDecimal(1);
	
	public static void main(String args[]) throws Exception {
		//BigDecimal cpm = revenue.divide(impressions, 6, RoundingMode.CEILING);
		//cpm = cpm.multiply(new BigDecimal(1000)).setScale(2, RoundingMode.CEILING);
		//System.out.println("CPM:" + cpm);
		//System.out.println("CPM:" + computeCPM(impressions, revenue, "CPM"));
		//testSummaryExport();
		 // InsertExcelFileToDB();
		 LocalDate localDate=LocalDate.now();
		System.out.println(localDate);
		System.out.println(getDateRangeById(0, 1, 3));
	}


	private static String getDateRangeById(int factor1, int factor2, Integer dateRangeId) throws Exception {
		String localDate = null;
		if (dateRangeId != null && dateRangeId.equals(3)) {
			localDate = LocalDate.now().plusDays(-1 * (factor1 * 6)).toString() + "-"
					+LocalDate.now().plusDays(-1 * (factor2 * 6)).toString();
		} else if (dateRangeId != null && dateRangeId.equals(6)) {
			localDate = LocalDate.now().plusDays(-1 * (factor1 * 29)) + "-"
					+ LocalDate.now().plusDays(-1 * ((factor2) * 29));
		} else if (dateRangeId != null && dateRangeId.equals(9)) {
			localDate = LocalDate.now().plusMonths(-1 * (factor1)) + "-" + LocalDate.now().plusMonths(-1 * ((factor2)));
		}
		return localDate;
		
	}

	
	public static BigDecimal computeCPM(BigDecimal impression, BigDecimal revenue, String CostMethod) {
		BigDecimal computedCPM = revenue;
		if (impression.compareTo(BigDecimal.ZERO) > 0) {
			if (CostMethod.equalsIgnoreCase("CPM")) {
				computedCPM = (revenue.divide(impression, 6, RoundingMode.CEILING)).multiply(new BigDecimal(1000))
						.setScale(2, RoundingMode.CEILING);
			} else
				computedCPM = (revenue.divide(impression)).setScale(2, RoundingMode.CEILING);
		}
		return computedCPM;
	}

	private static int getRandomNum(Random r) {
		return r.nextInt(10000);
	}

	public static String encryptVariable(String text) {
		String encrypted = "";
		for (char ch : text.toCharArray()) {
			encrypted = (ch == '-') ? encrypted + "T"
					: encrypted + alphaSeriesTxt.charAt(Integer.parseInt(Character.toString(ch)));
		}
		return encrypted;
	}

	public static String decryptVariable(String encrypted) {
		String text = "";
		for (char ch : encrypted.toCharArray()) {
			text = (ch == 'T') ? text + "-" : text + alphaSeriesTxt.indexOf(ch);
		}
		return text;
	}

	private static void tabSquare(int length) {
		int square;
		int a = 1;
		while (a < length) {
			square = a * a;

			System.out.println(square);
			a = a + 1;
		}
	}

	private static void computePrint() {
		Scanner input = new Scanner(System.in);
		int value;
		System.out.println("Enter the value to compute:");
		value = input.nextInt();
		do {
			System.out.println("Hello" + value);
			value = value + 1;
		} while (value < 10);
	}

	public static void largestOfThree() {
		int a = 5;
		int b = 5;
		int c = 5;
		if (a > b && a > c) {
			System.out.println("Greatest value is :" + a);
		} else if (b > c) {
			System.out.println("Greatest value is :" + b);
		} else {
			System.out.println("Greatest value is :" + c);

		}
	}

	public static void readFileInfo() {
		BufferedReader reader = null;
		InputStream input = null;
		try {
			input = new FileInputStream(new File("E:/New folder/myname.txt"));
			reader = new BufferedReader(new InputStreamReader(input));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
				break;
			}
			System.out.println(out.toString());
		} catch (FileNotFoundException fe) {
			System.out.println("File Not Found Error");
		} catch (IOException fe) {
			System.out.println("Stream Error");
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (reader != null) {
					reader.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void InsertExcelFileToDB() {
		ObjectUtils.printBeanProperites(FileUtils.fileToString("/home/YUME.COM/vrengasamy/Desktop/Task_5.1", true));
	}

	@SuppressWarnings("static-access")
	static class A {
		private static Integer testInfo;

		private void setTestInfo(Integer test) {
			this.testInfo = test;
		}
	}

}
