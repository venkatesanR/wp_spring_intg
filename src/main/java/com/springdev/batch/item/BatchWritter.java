package com.springdev.batch.item;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.springdev.cfc.GenericBean;

public class BatchWritter implements ItemWriter<GenericBean>{

	@Override
	public void write(List<? extends GenericBean> arg0) throws Exception {
		
	}
}
