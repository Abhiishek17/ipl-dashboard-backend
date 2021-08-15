package com.dashboard.ipl.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dashboard.ipl.service.IplDashboardService;

/**
 * The listener interface for receiving jobCompletionNotification events.
 * The class that is interested in processing a jobCompletionNotification
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addJobCompletionNotificationListener<code> method. When
 * the jobCompletionNotification event occurs, that object's appropriate
 * method is invoked.
 *
 * @see JobCompletionNotificationEvent
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	/** The ipl dashboard service. */
	private final IplDashboardService iplDashboardService;

	/**
	 * Instantiates a new job completion notification listener.
	 *
	 * @param iplDashboardService the ipl dashboard service
	 */
	/*
	 * Constructor injection.
	 * Why should we use it?
	 */
	@Autowired
	public JobCompletionNotificationListener(IplDashboardService iplDashboardService) {
		this.iplDashboardService = iplDashboardService;
	}

	/**
	 * After job.
	 *
	 * @param jobExecution the job execution
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("Successfully completed the Job");
			iplDashboardService.createTeamData();
		}
	}
}
