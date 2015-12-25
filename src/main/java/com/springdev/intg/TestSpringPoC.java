package com.springdev.intg;


public class TestSpringPoC {

	@SuppressWarnings({ "rawtypes" })
	public static void main(String args[]) {
		BeanHelper helper = new BeanHelper();
		SpringTestBean bean = (SpringTestBean) helper.getBean("testBean");
		bean.testCacheImpl();
	}
}
