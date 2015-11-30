package com.springdev.batch.item;

import java.util.List;

import org.apache.log4j.Logger;

import com.commons.utils.ObjectUtils;
import com.springdev.batch.scheduler.NativeTaskScheduler;
import com.springdev.cfc.GenericBean;

public class BatchInterCeptorImpl<T> extends ObjectUtils implements  BatchInterCeptor<T> {
	private static final Logger logger = Logger.getLogger(NativeTaskScheduler.class);

	private DealService dealService;
	
	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> read(String jobName) {
		if(dealService!=null && jobName!=null && !jobName.isEmpty()) {
			return (List<T>) dealService.getDeals();
		}
		return null;
	}

	@Override
	public void write(String jobName,List<T> workerList) {
		for(T object:workerList) {
			logger.info("BEAN PROPERTIES"+printBeanProperites((GenericBean)object));
		}
	}

}
