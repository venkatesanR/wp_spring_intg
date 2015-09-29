package com.springdev.intg;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanHelper<T> extends ApplicationContextLoader {
	private Map<String, T> beanMap = null;

	public BeanHelper() {
		super();
		getBeanMap();
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T getBean(String beanKey) {
		return (T) appContextRef.getBean(beanKey);
	}

	/**
	 * This method is responsible to load<br>
	 * all Bean definition and injections
	 * */
	@SuppressWarnings("unchecked")
	public Map<String, T> getBeanMap() {
		if (beanMap == null) {
			beanMap = new HashMap<String, T>();
			for (String beanName : appContextRef.getBeanDefinitionNames()) {
				T refObj = (T) appContextRef.getBean(beanName);
				beanMap.put(beanName, refObj);
			}
		}
		return beanMap;
	}
	public static String printBeanProperites(Object object) {
		if (object == null)
			return "";
		StringBuffer buffer = new StringBuffer(object.getClass().getName()
				+ "\n");
		try {
			Field[] fields = object.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				Object fieldObjects = fields[i].get(object);
				buffer.append("\t\t").append(field.getName()).append("\t:\t")
						.append(fieldObjects).append("\n");
				if (!(fieldObjects instanceof Object[]))
					continue;
				// handle the Objects present as array here
				Object[] objects = (Object[]) fieldObjects;
				for (int j = 0; j < objects.length; j++)
					buffer.append("\t\t\t\t").append(objects[j]).append("\n");
			}
			return buffer.toString();
		} catch (IllegalAccessException iae) {
			buffer.append("\n").append("Error Accessing variable " + iae);
			return buffer.toString();
		}
	}
}
