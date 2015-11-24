package com.springdev.batch.scheduler;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class NativeTaskScheduler {

	private static final Logger logger = Logger.getLogger(NativeTaskScheduler.class);

	private boolean run;

	public void setRun(boolean run) {
		this.run = run;
	}

	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

	}

}
