package com.algorithms.utils;

import java.util.List;

public interface Tree {
	List<LeafNode> search(ObjectSearchCriteria searchCriteria);
	void addLevel();
	void addLeafNode();
}
