package com.springdev.batch.scheduler;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import com.commons.utils.SystemUtils;

public class NativeTaskScheduler {
	private static final Logger logger = Logger.getLogger(NativeTaskScheduler.class);
	private JobLauncher launcher;
	private JobExecution execution;
	private List<Job> jobList;
	private boolean run;

	public NativeTaskScheduler(JobLauncher launcher, List<Job> incJobList) {
		this.launcher = launcher;
		this.jobList = incJobList;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public boolean isFroceShutDown() {
		String batch_Flag = SystemUtils.getDynaEnvVariables("DISABLE_BATCH_JOB");
		return false;
	}

	public void run() {
		try {
			if (run && jobList != null && !jobList.isEmpty() && !isFroceShutDown()) {
				for (Job job : jobList) {
					execution = launcher.run(job, new JobParameters());
				}
				logger.info("Execution Completed");
			}

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
