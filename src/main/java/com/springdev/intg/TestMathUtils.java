package com.springdev.intg;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestMathUtils {
	private static final Long LONG_ZERO = new Long(0);

	public static void main(String args[]) {
		String key="3_1_6_20151025_20151123_6_102";
		Long a = new Long(253);
		Long b = new Long(15);
		Long c = new Long(60);
		Long tot = new Long(328);
		System.out.println(computePercentOfRequet1(tot, a).add(computePercentOfRequet1(tot, b))
				.add(computePercentOfRequet1(tot, c)));
		System.out.println(key.substring(0, 5));
	}

	public static BigDecimal computePercentOfRequet1(Long totalRequest, Long request) {
		BigDecimal computedResponseRate = null;
		if (totalRequest != null && totalRequest > 0) {
			computedResponseRate = new BigDecimal(request);
			computedResponseRate = (computedResponseRate.divide(new BigDecimal(totalRequest), 6, RoundingMode.CEILING));
			computedResponseRate=computedResponseRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
		} else {
			computedResponseRate = new BigDecimal(0);
			computedResponseRate = computedResponseRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		}
		return computedResponseRate;
	}

	public static BigDecimal computeCPM(BigDecimal impression, BigDecimal revenue, String CostMethod) {
		BigDecimal computedCPM = revenue;
		if (impression.compareTo(BigDecimal.ZERO) > 0) {
			if (CostMethod.equalsIgnoreCase("CPM")) {
				computedCPM = (revenue.divide(impression, 6, RoundingMode.CEILING)).multiply(new BigDecimal(1000))
						.setScale(2, RoundingMode.CEILING);
			} else
				computedCPM = (revenue.divide(impression)).setScale(2, RoundingMode.CEILING);
		}
		return computedCPM;
	}

	/**
	 * This Method computes Response rate
	 * 
	 * For NUll Pointer Exception below line added if (request!=null && request
	 * > 0)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static BigDecimal computeResponseRate(Long request, Long response) {
		BigDecimal computedResponseRate = new BigDecimal(response);
		if (request != null && request > 0)
			computedResponseRate = computedResponseRate.divide(new BigDecimal(request), 6, RoundingMode.CEILING)
					.setScale(2, RoundingMode.CEILING);
		return computedResponseRate;
	}

	/**
	 * This Method computes Fill rate with Impression & Request
	 * 
	 * 
	 * @param impression
	 * @param request
	 * @return
	 */
	public static BigDecimal computeFillRate(long impression, long request) {
		BigDecimal computedFillRate = null;
		if (request > 0) {
			computedFillRate = new BigDecimal(impression);
			computedFillRate = computedFillRate.divide(new BigDecimal(request), 6, RoundingMode.CEILING)
					.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		} else {
			computedFillRate = new BigDecimal(0);
			computedFillRate = computedFillRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		}
		return computedFillRate;
	}

	/**
	 * This Method computes Percent of Request with Total Request & Request
	 * 
	 * For NUll Pointer Exception below line added if (totalRequest!=null &&
	 * totalRequest > 0){
	 * 
	 * @param totalRequest
	 * @param request
	 * @return
	 */
	public static BigDecimal computePercentOfRequet(Long totalRequest, Long request) {
		BigDecimal computedResponseRate = null;
		if (totalRequest != null && totalRequest > 0) {
			computedResponseRate = new BigDecimal(request);
			computedResponseRate = computedResponseRate.divide(new BigDecimal(totalRequest), 6, RoundingMode.CEILING)
					.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		} else {
			computedResponseRate = new BigDecimal(0);
			computedResponseRate = computedResponseRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		}
		return computedResponseRate;
	}

	/**
	 * This Method computes Impression rate with Impression & win
	 * 
	 * For NUll Pointer Exception below line added if (win!=null &&
	 * impression!=null && win > 0){
	 * 
	 * @param impression
	 * @param win
	 * @return
	 */
	public static BigDecimal computeImpressionRate(Long impression, Long win) {
		BigDecimal computedResponseRate = null;
		if (win != null && impression != null && win > 0) {
			computedResponseRate = new BigDecimal(impression);
			computedResponseRate = computedResponseRate.divide(new BigDecimal(win), 6, RoundingMode.CEILING)
					.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		} else {
			computedResponseRate = new BigDecimal(0);
			computedResponseRate = computedResponseRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		}
		return computedResponseRate;
	}

	/**
	 * This Method computes win rate with bin & win
	 * 
	 * For NUll Pointer Exception below line added if (win !=null && bid!=null
	 * && win > 0 ){
	 * 
	 * @param bid
	 * @param win
	 * @return
	 */
	public static BigDecimal computeWinRate(Long bid, Long win) {
		BigDecimal computedResponseRate = null;
		if (win != null && bid != null && win > 0) {
			computedResponseRate = new BigDecimal(win);
			computedResponseRate = computedResponseRate.divide(new BigDecimal(bid), 6, RoundingMode.CEILING)
					.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		} else {
			computedResponseRate = new BigDecimal(0);
			computedResponseRate = computedResponseRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		}
		return computedResponseRate;
	}

	/**
	 * This Method computes view through rate with impression & view
	 * 
	 * For NUll Pointer Exception below line added if (view!=null &&
	 * impression!=null && view > 0) {
	 * 
	 * @param impression
	 * @param view
	 * @return
	 */
	public static BigDecimal computeViewThroughRate(Long impression, Long view) {
		BigDecimal computedResponseRate = null;
		if (view != null && impression != null && view > 0) {
			computedResponseRate = new BigDecimal(view);
			computedResponseRate = computedResponseRate.divide(new BigDecimal(impression), 6, RoundingMode.CEILING)
					.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		} else {
			computedResponseRate = new BigDecimal(0);
			computedResponseRate = computedResponseRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		}
		return computedResponseRate;
	}

	/**
	 * This Method computes click through rate with impression & click
	 * 
	 * For NUll Pointer Exception below line added if(click!=null &&
	 * impression!=null && click > 0){
	 * 
	 * @param impression
	 * @param view
	 * @return
	 */
	public static BigDecimal computeClickThroughRate(Long impression, Long click) {
		BigDecimal computedResponseRate = null;
		if (click != null && impression != null && click > 0) {
			computedResponseRate = new BigDecimal(click);
			computedResponseRate = computedResponseRate.divide(new BigDecimal(impression), 6, RoundingMode.CEILING)
					.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		} else {
			computedResponseRate = new BigDecimal(0);
			computedResponseRate = computedResponseRate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		}
		return computedResponseRate;
	}

	public static BigDecimal computeeCpmTrend(BigDecimal eCpmValue1, BigDecimal eCpmValue2) {
		BigDecimal eCpmTrend = eCpmValue1;
		if (eCpmValue2.compareTo(BigDecimal.ZERO) > 0) {
			eCpmTrend = (eCpmValue1.divide(eCpmValue2, 6, RoundingMode.CEILING)).subtract(new BigDecimal(1))
					.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
		}
		return eCpmTrend;
	}

	public static BigDecimal computeViewabilityRate(BigDecimal impression1, BigDecimal impression2) {
		BigDecimal viewabilityRate = new BigDecimal(0.0);
		if (impression2.compareTo(BigDecimal.ZERO) > 0) {
			viewabilityRate = (impression1.divide(impression2, 6, RoundingMode.CEILING)).multiply(new BigDecimal(100))
					.setScale(2, RoundingMode.CEILING);
		}
		return viewabilityRate;
	}

	public static BigDecimal computeAvgPremiumToFloor(BigDecimal revenue, BigDecimal revenueFloorPriceDiff) {
		BigDecimal viewabilityRate = new BigDecimal(0.0);
		if (revenueFloorPriceDiff != null && revenue != null) {
			if (revenue.compareTo(BigDecimal.ZERO) > 0) {
				viewabilityRate = (revenueFloorPriceDiff.divide(revenue, 6, RoundingMode.CEILING))
						.multiply(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
			}
		}
		return viewabilityRate;
	}

	public static BigDecimal computeIpByOpAsBigDec(BigDecimal iP, BigDecimal oP) {
		BigDecimal result = new BigDecimal(0);
		if (iP != null && oP != null && !BigDecimal.ZERO.equals(oP)) {
			result = iP.divide(oP, 6, RoundingMode.CEILING);
		}
		return result;
	}

	public static Double computeIpByOpAsDouble(Long iP, Long oP) {
		Double result = new Double(0);
		if (iP != null && oP != null && !LONG_ZERO.equals(oP)) {
			result = (double) iP / oP;
		}
		return result;
	}
}
