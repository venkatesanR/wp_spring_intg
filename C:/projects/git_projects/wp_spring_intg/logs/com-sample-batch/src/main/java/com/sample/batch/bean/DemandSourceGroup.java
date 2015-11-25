package com.sample.batch.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="demand_source_group")
public class DemandSourceGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="demand_source_group_id")
	private int demandSourceGroupId;


	//bi-directional many-to-one association to DealDemandSourceGroup
	@ManyToOne
	@JoinColumn(name="deal_demand_source_group_id")
	private DealDemandSourceGroup dealDemandSourceGroup;

	
	@Column(name="demand_source_id")
	private int demandSourceId;

	public DemandSourceGroup() {
	}

	public int getDemandSourceGroupId() {
		return this.demandSourceGroupId;
	}

	public void setDemandSourceGroupId(int demandSourceGroupId) {
		this.demandSourceGroupId = demandSourceGroupId;
	}


	public DealDemandSourceGroup getDealDemandSourceGroup() {
		return this.dealDemandSourceGroup;
	}

	public void setDealDemandSourceGroup(DealDemandSourceGroup dealDemandSourceGroup) {
		this.dealDemandSourceGroup = dealDemandSourceGroup;
	}

	public int getDemandSourceId() {
		return demandSourceId;
	}

	public void setDemandSourceId(int demandSourceId) {
		this.demandSourceId = demandSourceId;
	}

    

}