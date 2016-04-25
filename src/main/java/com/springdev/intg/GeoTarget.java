package com.springdev.intg;

public class GeoTarget {
	private String k1;
	private String k2;
	private String k3;
	private int score;
	private int HIGH = 3;
	private int MED = 2;

	public GeoTarget(String v1, String v2, String v3) {
		this.k1 = v1;
		this.k2 = v2;
		this.k3 = v3;
	}

	public void setScore() {
		int score = 0;
		if (k3 == null && k2 == null) {
			score = HIGH + MED;
		} else if (k3 == null) {
			score = HIGH;
		} else if (k2 == null) {
			score = MED;
		}
		this.score = score;
	}

	public String getK1() {
		return k1;
	}

	public void setK1(String k1) {
		this.k1 = k1;
	}

	public String getK2() {
		return k2;
	}

	public void setK2(String k2) {
		this.k2 = k2;
	}

	public String getK3() {
		return k3;
	}

	public void setK3(String k3) {
		this.k3 = k3;
	}

	public int getScore() {
		return score;
	}

}
