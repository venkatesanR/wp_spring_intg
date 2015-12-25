package com.springdev.batch.item;

import java.util.ArrayList;
import java.util.List;

import com.springdev.cfc.GenericBean;

public class SampleService {
	public List<GenericBean> getDeals() {
		List<GenericBean> beanList = new ArrayList<GenericBean>();
		for (int i = 0; i < 10; i++) {
			beanList.add(new GenericBean());
		}
		return beanList;
	}
}
