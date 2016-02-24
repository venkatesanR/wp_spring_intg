package com.algorithms.utils;

import org.apache.log4j.Logger;

/**
 * Logic:FIFO
 * @author vrengasamy
 *
 * @param <T>
 */
public class QueueUtil<T> {
	private static final Logger logger = Logger.getLogger(QueueUtil.class);
	private T[] frame = null;
	private final int DEFAULT_FRAME_SIZE = 10;
	private Float loadFactor = null;
	private int head = 0;
	private int tail = 0;

	public QueueUtil(double loadFactor) {
		this.loadFactor = new Float(loadFactor);
	}

	public void enQueue(T element) {
		frame = MathUtil.loitering(frame, isEmpty(), DEFAULT_FRAME_SIZE, tail, loadFactor);
		System.out.println(frame.length);
	}

	public T deQueue() {
		return null;
	}

	private boolean isEmpty() {
		return true;
	}
	
	public static void main(String args[]) {
		QueueUtil utils = new QueueUtil(0.8);
		utils.enQueue("Hi");
	}
}
