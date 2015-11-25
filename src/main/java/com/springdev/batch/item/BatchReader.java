package com.springdev.batch.item;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.springdev.cfc.GenericBean;

public class BatchReader implements ItemReader<GenericBean>{
	private static transient final Logger logger = Logger
			.getLogger(BatchProcessor.class);
	@Override
	public GenericBean read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.info("insider BatchReader");
		return new GenericBean();
	}

}
