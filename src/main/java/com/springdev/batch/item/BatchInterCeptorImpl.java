package com.springdev.batch.item;

import java.util.List;

import com.commons.utils.ObjectUtils;
import com.springdev.cfc.GenericBean;

public class BatchInterCeptorImpl<T> extends ObjectUtils implements BatchInterCeptor<T> {
	private SampleService dealService;

	public void setDealService(SampleService dealService) {
		this.dealService = dealService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> read(String jobName) {
		if (dealService != null && jobName != null && !jobName.isEmpty()) {
			return (List<T>) dealService.getDeals();
		}
		return null;
	}

	@Override
	public void write(String jobName, List<T> workerList) {
		for (T object : workerList) {
			printBeanProperites((GenericBean) object);
		}
	}

}
