package com.springdev.batch.item;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;

public class BatchWriter<T> implements ItemWriter<T> {
	private static final Logger logger = Logger.getLogger(BatchWriter.class);
	private String jobName;
	private BatchInterCeptor interceptor;

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setInterceptor(BatchInterCeptor interceptor) {
		this.interceptor = interceptor;
	}


	@BeforeStep
	public void getInterstepData(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		setJobName(jobExecution.getJobInstance().getJobName());
	}

	@Override
	public void write(List<? extends T> workerInput) throws Exception {
		if(interceptor!=null && workerInput!=null && !workerInput.isEmpty()) {
			List<T> workerList=(List<T>) workerInput.get(0);
			interceptor.write(jobName, workerList);
		}
		
	}
}
