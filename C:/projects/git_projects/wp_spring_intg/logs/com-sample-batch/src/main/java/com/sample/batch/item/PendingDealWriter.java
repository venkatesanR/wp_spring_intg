package com.sample.batch.item;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.sample.batch.bean.Deal;
import com.sample.batch.service.DealService;

public class PendingDealWriter implements ItemWriter<Deal>{
	
	private DealService dealService;

	public void write(List<? extends Deal> items) throws Exception {
		
		System.out.println("Inside Writer "+items);
		
		if(items !=null && items.size() >0){
			
			for(Deal item : items){
				dealService.update(item);
			}			
			
		}
		
	}

	public DealService getDealService() {
		return dealService;
	}

	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}
	

}
