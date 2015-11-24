package com.springdev.batch.item;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.springdev.cfc.GenericBean;

public class BatchReader implements ItemReader<GenericBean> {

	@Override
	public GenericBean read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return null;
	}

}
