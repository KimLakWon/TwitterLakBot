package com.hello.config;

import java.util.concurrent.ScheduledFuture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
public class executorConfig extends ThreadPoolTaskScheduler {

	private static final long serialVersionUID = 1L;

	@Bean("serviceExecutor")
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		executor.setThreadNamePrefix("service-");
		executor.initialize();
		return executor;
	}
	
	@Bean("scheduler")
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(1);
		scheduler.setThreadNamePrefix("scheduler-");
		scheduler.initialize();
		return scheduler;
	}
	
	@Override 
	public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) { 
		if (period <= 0) { 
			return null; 
		} 
		
		ScheduledFuture<?> future = super.scheduleAtFixedRate(task, period); 
		return future; 
		}

}