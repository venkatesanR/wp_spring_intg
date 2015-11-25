package com.springdev.batch.item;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.springdev.cfc.GenericBean;

public class BatchProcessor implements ItemProcessor<GenericBean, GenericBean> {
	private static transient final Logger logger = Logger
			.getLogger(BatchProcessor.class);

	@Override
	public GenericBean process(GenericBean item) throws Exception {
		logger.info("*************************** Inside Batch Processor *************************************");
		return item;
	}

}
