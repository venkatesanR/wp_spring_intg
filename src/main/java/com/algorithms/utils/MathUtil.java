package com.algorithms.utils;

import com.commons.utils.ObjectUtils;

@SuppressWarnings("unchecked")
public class MathUtil {
	private final static int DEFAULT_FRAME_SIZE = 10;

	public static <T> T[] loitering(T[] frame, boolean isEmpty, int stack_current_index, Float loadFactor) {
		Float accuracy = new Float("0");
		if (frame != null && stack_current_index >= 0) {
			accuracy = Float.valueOf(stack_current_index) / Float.valueOf(frame.length);
			if (accuracy >= loadFactor) {
				T[] new_stack_frame = (T[]) new Object[frame.length + DEFAULT_FRAME_SIZE];
				frame = ObjectUtils.arrayCopy(frame, new_stack_frame);
			}

		} else if (isEmpty) {
			frame = (T[]) new Object[DEFAULT_FRAME_SIZE];
		}
		return frame;
	}
}
