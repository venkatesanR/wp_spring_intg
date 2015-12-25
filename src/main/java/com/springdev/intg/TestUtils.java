package com.springdev.intg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import com.commons.utils.KeyValuePair;
import com.commons.utils.ObjectUtils;
import com.commons.utils.SQLMapper;
import com.springdev.dbutils.DBDataUtils;

public class TestUtils extends BeanHelper<Object> {
	public static void main(String args[]) {
		/*
		 * GenericBean beanInfo = new GenericBean();
		 * beanInfo.setPropertyName("Mist"); beanInfo.setPropertyValue("Must");
		 * LocalStorageOfObject.storeObjectInLocal(beanInfo, "GENERIC_INFO");
		 * LocalStorageOfObject.printAllBeans(); A ref=new A();
		 * largestOfThree(); //readFileInfo(); computePrint();
		
		KeyValuePair pari=new KeyValuePair();
		pari.setKey("name");
		pari.setValue("val");
		Collection<KeyValuePair> collec=new ArrayList<KeyValuePair>();
		collec.add(pari);
		collec.add(pari);
		collec.add(pari);
		collec.add(pari);
		collec.add(pari);
		SQLMapper mapperInfo = new SQLMapper();
		mapperInfo.setSqlKey("Key");
		mapperInfo.setSqlQuery("Select * from abc");
		mapperInfo.setInputParams(collec);
		mapperInfo.setOuputParams(collec);
		ObjectUtils.ObjectToXml(SQLMapper.class, mapperInfo);
		prepareXmlToDbObject */
		System.out.println(ObjectUtils.printBeanProperites(DBDataUtils.prepareXmlToDbObject("/home/yumecorp/Desktop/YuMe-WorkPlace/wp_spring_intg/src/main/resources/ehCache_sqlLoader.xml")));
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

	@SuppressWarnings("static-access")
	static class A {
		private static Integer testInfo;

		private void setTestInfo(Integer test) {
			this.testInfo = test;
		}
	}

}
