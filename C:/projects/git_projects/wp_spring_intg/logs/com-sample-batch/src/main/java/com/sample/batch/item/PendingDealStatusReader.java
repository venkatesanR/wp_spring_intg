package com.sample.batch.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.sample.batch.bean.Deal;
import com.sample.batch.service.DealService;

public class PendingDealStatusReader implements ItemReader<Deal> {
	
	private DealService dealService;
	
	private List<Deal> pendingDealList = new ArrayList<Deal>();
	
	public PendingDealStatusReader(DealService dealService) {
		this.dealService = dealService;
		pendingDealList.addAll(getDealService().getListOfDeals());
	}
	

	public Deal read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Inside Reader ******");
		if(pendingDealList !=null && pendingDealList.size() > 0){			
			return pendingDealList.remove(0);
		}
		
		return null;
	}



	public DealService getDealService() {
		return dealService;
	}



	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}



	public List<Deal> getPendingDealList() {
		return pendingDealList;
	}



	public void setPendingDealList(List<Deal> pendingDealList) {
		this.pendingDealList = pendingDealList;
	}
	
	
	
	

}
