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
		if (head != tail + 1) {
			frame[tail] = element;
			tail = tail + 1;
		} else if(head == tail + 1) {
			logger.error("queue over flow");
		}

	}

	public T deQueue() {
		T element = null;
		if(tail != head ) {
			element = frame[head];
			frame[head]=(T) "0";
			head=head+1;	
		} else if(tail == head ) {
			logger.error("queue under flow");
		}
		return element;
	}

	private boolean isEmpty() {
		return (frame == null || tail == 0);
	}
	
	public static void main(String args[]) {
		QueueUtil<String> utils = new QueueUtil<String>(0.8);
		utils.enQueue("X");
		utils.enQueue("X");
		utils.enQueue("X");
		utils.enQueue("X");
		System.out.println(utils.toString());
		utils.deQueue();
		System.out.println(utils.toString());
		utils.deQueue();
		System.out.println(utils.toString());
		utils.deQueue();
		System.out.println(utils.toString());
		utils.deQueue();
	}

	public String toString() {
		String outpuStack = "|";
		if (this != null) {
			for (int index = 0; index < this.tail; index++) {
				outpuStack = outpuStack
						+ String.valueOf(" "+this.frame[index] + (this.head == index ? "(H)" : "")
								+ (index == this.tail - 1 ? "(T)" : "") +" ")
						+ ((index == this.tail - 1) ? "|": "");
			}
		}
		return outpuStack;
	}
}
