package com.algorithms.utils;

import org.apache.log4j.Logger;

import com.commons.utils.ObjectUtils;

/**
 * This class illustrate various technique to implements stack's.<br>
 * (i)Array Based push and Pop
 * 
 * <p>
 * concept of stack
 * </p>
 * Working principle:LIFO(mainly used to switching and context between two
 * statements)<br>
 * <b> Step1</b> <br>
 * ----------------<br>
 * 1|2|3|4<br>
 * ----------------<br>
 * <b>Step2: PUSH 12,13</b><br>
 * ----------------<br>
 * 1|2|3|4|12|13<br>
 * ----------------<br>
 * <b>Step3:POP</b><br>
 * ----------------<br>
 * 1|2|3|4|12<br>
 * ----------------<br>
 * Exceptional:Stackunder and Over :Flow
 */

public class StackUtils<T> {
	private static final Logger logger = Logger.getLogger(StackUtils.class);
	private T[] stack_frame = null;
	private int stack_current_index = -1;
	private Float loitering_factor = null;

	public StackUtils(double loadFactor) {
		loitering_factor = new Float(loadFactor);
	}

	public void push(T elementToPush) {
		dynamicAllocation();
		stack_current_index = stack_current_index + 1;
		stack_frame[stack_current_index] = elementToPush;
	}

	public T pop() {
		T info = null;
		if (!isEmpty()) {
			info = stack_frame[stack_current_index];
			stack_current_index = stack_current_index - 1;
		} else {
			logger.error("stack under flow is happening");
		}
		return info;
	}

	public boolean isEmpty() {
		return stack_current_index == -1;
	}

	private void dynamicAllocation() {
		stack_frame = MathUtil.loitering(stack_frame, isEmpty(), stack_current_index,
				loitering_factor);
	}

	@Override
	public String toString() {
		return ObjectUtils.printBeanProperites(this);
	}
}
