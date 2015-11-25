package com.sample.batch.item;

import org.springframework.batch.item.ItemProcessor;

import com.sample.batch.bean.Deal;

public class PendingDealStatusProcessor implements ItemProcessor<Deal,Deal>{

	public Deal process(Deal item) throws Exception {
		
		System.out.println("Inside Deal Pending Processor");
		
		item.setDealStatusId((byte)1);
		
		return item;
	}

}
