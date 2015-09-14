package com.springdev.intg;


public class TestSpringPoC {
	@SuppressWarnings({ "rawtypes", "static-access" })
	public static void main(String args[]) {
		BeanHelper helper = new BeanHelper();
		String[] beanDefinations = helper.appContextRef
				.getBeanDefinitionNames();
		intializeTestModel(helper,beanDefinations);
	}

	@SuppressWarnings({ "rawtypes" })
	private static void intializeTestModel(BeanHelper helper,String[] beanDefinations) {
		if (beanDefinations != null && beanDefinations.length > 0) {
			for (String beanType : beanDefinations) {
				System.out.println(BeanHelper.printBeanProperites(helper.getBean(beanType)));
			}
		}
	}
}
