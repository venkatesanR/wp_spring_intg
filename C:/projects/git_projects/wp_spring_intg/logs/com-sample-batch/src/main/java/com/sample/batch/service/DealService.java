package com.sample.batch.service;

import java.util.ArrayList;
import java.util.List;

import com.sample.batch.bean.Deal;

public class DealService {
	
	public List<Deal> getListOfDeals(){
		
		List<Deal> dealList = new ArrayList<Deal>();
		
		for(int i=0;i<10;i++){
			Deal deal = new Deal();
			deal.setAccountId(i+1);
			deal.setDealStatusId((byte)2);
			dealList.add(deal);
		}		
		
		return dealList;
	}
	
	public boolean update(Deal deal){
		 
		System.out.println("Printing deal account Id "+ deal.getAccountId() + "*****Status *****"+deal.getDealStatusId());
		
		return true;
	}

}
