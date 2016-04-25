package com.springdev.intg;

import com.springdev.ldap.LDAPConstants;
@SuppressWarnings({ "rawtypes" })
public class TestSpringPoC {
	private static final String oPass = LDAPConstants.CONFEDENTIALS.PASSWORD;

	public static void main(String args[]) {
		BeanHelper helper = new BeanHelper();
		SpringTestBean test_bean = (SpringTestBean) helper.getBean("testBean");
		//test_bean.testCacheImpl();
		test_bean.testCredentials(helper, "vrengasamy@yume.com", oPass);
	}
}
