package com.sample.batch.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="deal_target")
@NamedQuery(name="DealTarget.findAll", query="SELECT d FROM DealTarget d")
public class DealTarget implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="deal_target_id")
	private int dealTargetId;

	@Lob
	@Column(name="by_audience_segment")
	private String byAudienceSegment;

	@Column(name="by_daypart")
	private String byDaypart;

	@Column(name="by_device_type")
	private String byDeviceType;

	@Lob
	@Column(name="by_geo")
	private String byGeo;

	@Lob
	@Column(name="by_zone")
	private String byZone;

	//bi-directional many-to-one association to Deal
	@ManyToOne
	@JoinColumn(name="deal_id")
	private Deal deal;

	public DealTarget() {
	}

	public int getDealTargetId() {
		return this.dealTargetId;
	}

	public void setDealTargetId(int dealTargetId) {
		this.dealTargetId = dealTargetId;
	}

	public String getByAudienceSegment() {
		return this.byAudienceSegment;
	}

	public void setByAudienceSegment(String byAudienceSegment) {
		this.byAudienceSegment = byAudienceSegment;
	}

	public String getByDaypart() {
		return this.byDaypart;
	}

	public void setByDaypart(String byDaypart) {
		this.byDaypart = byDaypart;
	}

	public String getByDeviceType() {
		return this.byDeviceType;
	}

	public void setByDeviceType(String byDeviceType) {
		this.byDeviceType = byDeviceType;
	}

	public String getByGeo() {
		return this.byGeo;
	}

	public void setByGeo(String byGeo) {
		this.byGeo = byGeo;
	}

	public String getByZone() {
		return this.byZone;
	}

	public void setByZone(String byZone) {
		this.byZone = byZone;
	}


	public Deal getDeal() {
		return this.deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}
}
