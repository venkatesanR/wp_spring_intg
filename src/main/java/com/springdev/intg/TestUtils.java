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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestUtils extends BeanHelper<Object> {
	private static final String alphaSeriesTxt = "BXGLWANRPQ";
	static BigDecimal revenue = new BigDecimal(.004);
	static BigDecimal impressions = new BigDecimal(1);
	private  static String COMMA_DELIMITER=",";
	public static String getformattedDataforCSV(String accountCurrencySymbol) {
		return "\"" + "ASjAS" + "\"" 
				+ "x"
				+ COMMA_DELIMITER + "ki"
				+ COMMA_DELIMITER + "\"" + "sss" + " %" + "\"" 
				+ COMMA_DELIMITER + "sxsx"
				+ COMMA_DELIMITER + "\"" + "dx" + " %";
	}
	public static void main(String args[]) throws Exception {
		System.out.println(getformattedDataforCSV("ASJAS"));
		//restClientTest();
	}

	public static String getDateRangeById(int factor1, int factor2, Integer dateRangeId) throws Exception {
		String localDate = null;
		if (dateRangeId != null && dateRangeId.equals(3)) {
			localDate = LocalDate.now().plusDays(-1 * (factor1 * 6)).toString() + "-"
					+ LocalDate.now().plusDays(-1 * (factor2 * 6)).toString();
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

	//http://qa-ydmp-app.dev.yumenetworks.com/2/api/get_datavendor?email=mvenkatesan@yume.com&password=test12
	private static void restClientTest() {
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(
					"http://qa-ydmp-app.dev.yumenetworks.com/2/api/get_datavendor?email=mvenkatesan@yume.com&password=test12");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
