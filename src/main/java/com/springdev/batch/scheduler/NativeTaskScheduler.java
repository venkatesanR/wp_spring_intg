package com.springdev.batch.scheduler;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

public class NativeTaskScheduler {

	private static final Logger logger = Logger.getLogger(NativeTaskScheduler.class);
	private JobLauncher launcher;
	private JobExecution execution;
	private Job job;
	private boolean run;

	public NativeTaskScheduler(JobLauncher launcher, Job job) {
		this.launcher = launcher;
		this.job = job;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public void run() {
		try {
			logger.info("****************  JOB_NAME::"+job.getName());
			execution = launcher.run(job, new JobParameters());
		} catch (JobExecutionAlreadyRunningException e) {
			logger.debug(e);
		} catch (JobRestartException e) {
			logger.debug(e);
		} catch (JobInstanceAlreadyCompleteException e) {
			logger.debug(e);
		} catch (JobParametersInvalidException e) {
			logger.debug(e);
		}
	}

}
