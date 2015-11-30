package com.springdev.batch.item;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.springdev.cfc.GenericBean;

public class BatchProcessor<T> implements ItemProcessor<T, T> {
	private static transient final Logger logger = Logger.getLogger(BatchProcessor.class);

	@Override
	public T process(T item) throws Exception {
		return item;
	}

}
