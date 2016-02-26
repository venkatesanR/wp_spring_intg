package com.algorithms.utils;


public class LinkedListUtil<T> {
	private Node rootNode;

	public void add(T value) {
		if (rootNode != null) {
			rootNode.predecssor = rootNode;
			rootNode.successor = null;
			rootNode.value = value;
		} else {
			rootNode = new Node();
			rootNode.predecssor = null;
			rootNode.successor = null;
			rootNode.value = value;
		}
	}

	public void remove() {

	}

	/**
	 * When we say search <br>
	 * it search value in linear manner worst case analysis: O(n)<br>
	 * 
	 * @param <T>
	 * @param value
	 * @return
	 */
	public Node search(T value) {
		return null;
	}

	class Node {
		Node successor;
		Node predecssor;
		T value;
	}
}
