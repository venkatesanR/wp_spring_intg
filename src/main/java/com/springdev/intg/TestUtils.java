package com.springdev.intg;

import com.commons.utils.LocalStorageOfObject;
import com.springdev.cfc.GenericBean;

public class TestUtils extends BeanHelper<Object> {
	public static void main1(String args[]) {
		GenericBean beanInfo = new GenericBean();
		beanInfo.setPropertyName("Mist");
		beanInfo.setPropertyValue("Must");
		LocalStorageOfObject.storeObjectInLocal(beanInfo, "GENERIC_INFO");
		LocalStorageOfObject.printAllBeans();
	}
}
