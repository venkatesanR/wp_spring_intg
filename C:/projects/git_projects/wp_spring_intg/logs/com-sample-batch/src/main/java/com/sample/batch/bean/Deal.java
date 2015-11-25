package com.sample.batch.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="deal")
public class Deal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="deal_id")
	private Integer dealId;

	
	@Column(name="daily_cap")
	private int dailyCap;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;

	private BigInteger goal;

	
	private String name;

	private Integer priority;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;

	@Column(name="account_id")
	private int accountId;
	
	@Column(name="deal_status_id")
	private byte dealStatusId;

	@Column(name="zone_group_id")
	private int zoneGroupId;

	//bi-directional many-to-one association to DealDemandSourceGroup
	@OneToMany(mappedBy="deal",cascade=CascadeType.ALL)
	private Set<DealDemandSourceGroup> dealDemandSourceGroups = new HashSet<DealDemandSourceGroup>(0);

	//bi-directional many-to-one association to DealTarget
	@OneToOne(mappedBy="deal",cascade=CascadeType.ALL)
	@JoinColumn(name="deal_target_id")
	private DealTarget dealTarget;
	
	@Transient
	private boolean isDealNamechanged;	
	@Transient
	private boolean immediateStart;
	
	@Transient
	private Long requests;
	@Transient
	private Long responses;	
	@Transient
	private BigDecimal responseRate = new BigDecimal(0);
	@Transient
	private Long impressions;
	@Transient
	private BigDecimal impressionResponse = new BigDecimal(0);
	@Transient
	private BigDecimal fillRate = new BigDecimal(0);
	@Transient
	private Double averageCpm;
	@Transient
	private Long revenue = new Long(0);
	@Transient
	private BigDecimal vcr = new BigDecimal(0);
	@Transient
	private BigDecimal ctr = new BigDecimal(0);
	@Transient
	private Double averageBid = new Double(0);
	@Transient
	private Double averageBids = new Double(0);
	@Transient
	private Double averageClearingPrice;
	@Transient
	private BigDecimal averagePremiumToFloor = new BigDecimal(0);
	
	@Transient
	private boolean same;

	public Deal() {
	}

	public Integer getDealId() {
		return dealId;
	}

	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}


	public int getDailyCap() {
		return this.dailyCap;
	}

	public void setDailyCap(int dailyCap) {
		this.dailyCap = dailyCap;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigInteger getGoal() {
		return this.goal;
	}

	public void setGoal(BigInteger goal) {
		this.goal = goal;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public int getAccountId() {
		return accountId;
	}

	

	public byte getDealStatusId() {
		return dealStatusId;
	}

	public void setDealStatusId(byte dealStatusId) {
		this.dealStatusId = dealStatusId;
	}

	public int getZoneGroupId() {
		return zoneGroupId;
	}

	public void setZoneGroupId(int zoneGroupId) {
		this.zoneGroupId = zoneGroupId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Set<DealDemandSourceGroup> getDealDemandSourceGroups() {
		return dealDemandSourceGroups;
	}

	public void setDealDemandSourceGroups(
			Set<DealDemandSourceGroup> dealDemandSourceGroups) {
		this.dealDemandSourceGroups = dealDemandSourceGroups;
	}

	public DealDemandSourceGroup addDealDemandSourceGroup(DealDemandSourceGroup dealDemandSourceGroup) {
		getDealDemandSourceGroups().add(dealDemandSourceGroup);
		dealDemandSourceGroup.setDeal(this);

		return dealDemandSourceGroup;
	}

	public DealDemandSourceGroup removeDealDemandSourceGroup(DealDemandSourceGroup dealDemandSourceGroup) {
		getDealDemandSourceGroups().remove(dealDemandSourceGroup);
		dealDemandSourceGroup.setDeal(null);

		return dealDemandSourceGroup;
	}

	public DealTarget getDealTarget() {
		return dealTarget;
	}

	public void setDealTarget(DealTarget dealTarget) {
		this.dealTarget = dealTarget;
	}

	public boolean getIsDealNamechanged() {
		return isDealNamechanged;
	}

	public void setIsDealNamechanged(boolean isDealNamechanged) {
		this.isDealNamechanged = isDealNamechanged;
	}

	public boolean getImmediateStart() {
		return immediateStart;
	}

	public void setImmediateStart(boolean immediateStart) {
		this.immediateStart = immediateStart;
	}

	public Long getRequests() {
		return requests;
	}

	public void setRequests(Long requests) {
		this.requests = requests;
	}

	public Long getResponses() {
		return responses;
	}

	public void setResponses(Long responses) {
		this.responses = responses;
	}

	public BigDecimal getResponseRate() {
		return responseRate;
	}

	public void setResponseRate(BigDecimal responseRate) {
		this.responseRate = responseRate;
	}

	public Long getImpressions() {
		return impressions;
	}

	public void setImpressions(Long impressions) {
		this.impressions = impressions;
	}

	public BigDecimal getImpressionResponse() {
		return impressionResponse;
	}

	public void setImpressionResponse(BigDecimal impressionResponse) {
		this.impressionResponse = impressionResponse;
	}

	public BigDecimal getFillRate() {
		return fillRate;
	}

	public void setFillRate(BigDecimal fillRate) {
		this.fillRate = fillRate;
	}

	public Double getAverageCpm() {
		return averageCpm;
	}

	public void setAverageCpm(Double averageCpm) {
		this.averageCpm = averageCpm;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getVcr() {
		return vcr;
	}

	public void setVcr(BigDecimal vcr) {
		this.vcr = vcr;
	}

	public BigDecimal getCtr() {
		return ctr;
	}

	public void setCtr(BigDecimal ctr) {
		this.ctr = ctr;
	}

	public Double getAverageBid() {
		return averageBid;
	}

	public void setAverageBid(Double averageBid) {
		this.averageBid = averageBid;
	}

	public Double getAverageBids() {
		return averageBids;
	}

	public void setAverageBids(Double averageBids) {
		this.averageBids = averageBids;
	}

	public Double getAverageClearingPrice() {
		return averageClearingPrice;
	}

	public void setAverageClearingPrice(Double averageClearingPrice) {
		this.averageClearingPrice = averageClearingPrice;
	}

	public BigDecimal getAveragePremiumToFloor() {
		return averagePremiumToFloor;
	}

	public void setAveragePremiumToFloor(BigDecimal averagePremiumToFloor) {
		this.averagePremiumToFloor = averagePremiumToFloor;
	}

	public boolean getSame() {
		return same;
	}

	public void setSame(boolean same) {
		this.same = same;
	}
}
	
