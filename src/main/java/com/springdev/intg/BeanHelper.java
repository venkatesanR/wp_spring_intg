package com.springdev.intg;

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
}
