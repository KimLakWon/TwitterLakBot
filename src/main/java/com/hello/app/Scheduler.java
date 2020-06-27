package com.hello.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import com.hello.service.DeleteStatusService;
import com.hello.service.SearchService;
import com.hello.service.UpdateStatusService;

@Component
public class Scheduler {

	private Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	private UpdateStatusService updateStatusService;
	
	private DeleteStatusService deleteStatusService;
	
	private SearchService searchService;
	
	private TaskExecutor taskExecutor;
	
	private TaskScheduler taskScheduler;

	private Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

	@Autowired
	public Scheduler(UpdateStatusService updateStatusService,
			DeleteStatusService deleteStatusService,
			SearchService searchService,
			@Qualifier("serviceExecutor")TaskExecutor taskExecutor,
			@Qualifier("scheduler")TaskScheduler taskScheduler){
		this.updateStatusService = updateStatusService;
		this.deleteStatusService = deleteStatusService;
		this.searchService = searchService;
		this.taskExecutor = taskExecutor;
		this.taskScheduler = taskScheduler;
	}
	
	@PostConstruct
	public void init() { 
		start(30);
	}
	
	public void start(long period) {
		ScheduledFuture<?> task = taskScheduler.scheduleAtFixedRate(this::execute, period*1000); 
		scheduledTasks.put("scheduler", task);
	}
	public void remove() {
		scheduledTasks.get("scheduler").cancel(true);
	}

	public void execute() {
	   logger.info("Execute Test. " + updateStatusService.getStart());
	   
	   if(updateStatusService.getStart()) {
		   logger.info("Execute updateStatusService.");
		   taskExecutor.execute(()-> updateStatusService.update());
	   }
	   
	   if(searchService.getStart()) {
		   logger.info("Execute searchService.");
		   taskExecutor.execute(()-> searchService.search());
	   }
	   
	   if(deleteStatusService.getStart()) {
		   logger.info("Execute deleteStatusService.");
		   taskExecutor.execute(()-> deleteStatusService.delete());
	   }
   }
}
