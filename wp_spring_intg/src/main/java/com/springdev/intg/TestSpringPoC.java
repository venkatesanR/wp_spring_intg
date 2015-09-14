package com.springdev.intg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.springdev.cfc.DependentBean;
import com.springdev.cfc.GenericBean;

public class TestSpringPoC {
	@SuppressWarnings({ "rawtypes", "static-access" })
	public static void main(String args[]) {
		BeanHelper helper = new BeanHelper();
		String[] beanDefinations = helper.appContextRef
				.getBeanDefinitionNames();
		List<String> excludes=new ArrayList<String>();
		excludes.add("nestedBean");
		excludes.add("serviceLocator");
		intializeTestModel(helper, beanDefinations, TestSpringPoC.class,excludes);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void intializeTestModel(BeanHelper helper,
			String[] beanDefn, Class clazz, List<String> excludes) {
		if (beanDefn != null && beanDefn.length > 0) {
			Method testMethod = null;
			Object[] ref = null;
			Object instance;
			try {
				instance = clazz.newInstance();
				Class[] classDeParams = null;
				for (String beanType : beanDefn) {
					if (!excludes.contains(beanType)) {
						classDeParams = new Class[1];
						ref = new Object[1];
						classDeParams[0] = (helper.getBean(beanType))
								.getClass();
						ref[0] = helper.getBean(beanType);
						try {
							testMethod = clazz.getMethod(beanType,
									classDeParams);
						} catch (NoSuchMethodException e) {
							System.out.println("Exception Burried"
									+ e.getMessage());
						}
						if (testMethod != null) {
							testMethod.invoke(instance, ref);
						}
					}

				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}

	public static void genericBean(GenericBean simpleBean) {
		System.out.println(simpleBean.getPropertyName());
	}

	public static void genericOuterBean(GenericBean nestedBean) {
		System.out.println(nestedBean.getPropertyName());
		System.out.println(nestedBean.getNestedRef());
	}

	public static void clientService(
			DependentBean dependentFromFactory) {
		System.out.println(dependentFromFactory.toString());
	}
}
