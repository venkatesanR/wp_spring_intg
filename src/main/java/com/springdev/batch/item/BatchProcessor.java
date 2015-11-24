package com.springdev.batch.item;

import org.springframework.batch.item.ItemProcessor;

import com.springdev.cfc.GenericBean;

public class BatchProcessor implements ItemProcessor<GenericBean, GenericBean>{
	@Override
	public GenericBean process(GenericBean arg0) throws Exception {
		return null;
	}
}
