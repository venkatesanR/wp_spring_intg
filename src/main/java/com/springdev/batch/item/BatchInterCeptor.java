package com.springdev.batch.item;

import java.util.List;

public interface BatchInterCeptor<T> {
	<T> List<T> read(String jobName);
	void write(String jobName,List<T> workerList);
}
