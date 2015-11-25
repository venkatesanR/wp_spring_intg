package com.sample.batch.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="deal_demand_source_group")
public class DealDemandSourceGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="deal_demand_source_group_id")
	private int dealDemandSourceGroupId;

	
	private BigDecimal price;

	@Column(name="rtb_deal_id")
	private String rtbDealId;

	//bi-directional many-to-one association to Deal
	@ManyToOne
	@JoinColumn(name="deal_id")
	private Deal deal;

	
	@Column(name="pricing_type_id")
	private Byte dealPricingTypeId;
	
	@Column(name="deal_type_id")
	private byte dealTypeId;

	//bi-directional many-to-one association to DemandSourceGroup
	@OneToMany(mappedBy="dealDemandSourceGroup",cascade=CascadeType.ALL)
	private Set<DemandSourceGroup> demandSourceGroups =  new HashSet<DemandSourceGroup>();

	public DealDemandSourceGroup() {
	}

	public int getDealDemandSourceGroupId() {
		return this.dealDemandSourceGroupId;
	}

	public void setDealDemandSourceGroupId(int dealDemandSourceGroupId) {
		this.dealDemandSourceGroupId = dealDemandSourceGroupId;
	}


	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRtbDealId() {
		return this.rtbDealId;
	}

	public void setRtbDealId(String rtbDealId) {
		this.rtbDealId = rtbDealId;
	}

	public Deal getDeal() {
		return this.deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}
	
	public Byte getDealPricingTypeId() {
		return dealPricingTypeId;
	}

	public void setDealPricingTypeId(Byte dealPricingTypeId) {
		this.dealPricingTypeId = dealPricingTypeId;
	}

	public byte getDealTypeId() {
		return dealTypeId;
	}

	public void setDealTypeId(byte dealTypeId) {
		this.dealTypeId = dealTypeId;
	}

	public Set<DemandSourceGroup> getDemandSourceGroups() {
		return demandSourceGroups;
	}

	public void setDemandSourceGroups(Set<DemandSourceGroup> demandSourceGroups) {
		this.demandSourceGroups = demandSourceGroups;
	}

	public DemandSourceGroup addDemandSourceGroup(DemandSourceGroup demandSourceGroup) {
		getDemandSourceGroups().add(demandSourceGroup);
		demandSourceGroup.setDealDemandSourceGroup(this);

		return demandSourceGroup;
	}

	public DemandSourceGroup removeDemandSourceGroup(DemandSourceGroup demandSourceGroup) {
		getDemandSourceGroups().remove(demandSourceGroup);
		demandSourceGroup.setDealDemandSourceGroup(null);

		return demandSourceGroup;
	}
}
