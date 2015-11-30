package com.springdev.batch.item;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.commons.utils.ObjectUtils;

@SuppressWarnings("unchecked")
public class BatchReader<T> extends ObjectUtils implements ItemReader<T> {
	private static transient final Logger logger = Logger.getLogger(BatchProcessor.class);
	private String jobName;
	@SuppressWarnings("rawtypes")
	private BatchInterCeptor interceptor;
	private List<T> workerList;

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setInterceptor(BatchInterCeptor interceptor) {
		this.interceptor = interceptor;
	}

	public void setWorkerList(List<T> workerList) {
		this.workerList = workerList;
	}

	@Override
	public  T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (workerList != null && !workerList.isEmpty()) {
			T workerListToWrite=(T) workerList.subList(0, workerList.size());
			workerList=null;
			return workerListToWrite;
		}
		return null;
	}

	@BeforeStep
	public void getInterstepData(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		setJobName(jobExecution.getJobInstance().getJobName());
		if (interceptor != null) {
			setWorkerList(interceptor.read(jobExecution.getJobInstance().getJobName()));
		}
	}
}
