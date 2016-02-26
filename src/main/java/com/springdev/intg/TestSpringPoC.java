package com.springdev.intg;

import org.apache.log4j.Logger;

import com.springdev.ldap.LDAPConstants;

public class TestSpringPoC {
	private static final Logger logger = Logger.getLogger(TestSpringPoC.class);
	private static final String oPass=LDAPConstants.CONFEDENTIALS.PASSWORD;
	@SuppressWarnings({ "rawtypes" })
	public static void main(String args[]) {
		BeanHelper helper = new BeanHelper();
		SpringTestBean bean = (SpringTestBean) helper.getBean("testBean");
		System.out.println("Validation Logic:"
				+ bean.testCredentials(helper, "vrengasamy@yume.com", oPass));
	}
}
