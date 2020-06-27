package com.hello.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hello.service.DeleteStatusService;
import com.hello.service.SearchService;
import com.hello.service.UpdateStatusService;

@Component
public class testBot {

	private Logger logger = LoggerFactory.getLogger(testBot.class);
	
	private UpdateStatusService updateStatusService;
	
	private DeleteStatusService deleteStatusService;
	
	private SearchService searchService;
	
	private TaskExecutor taskExecutor;

	@Autowired
	public testBot(UpdateStatusService updateStatusService,
			DeleteStatusService deleteStatusService,
			SearchService searchService,
			@Qualifier("serviceExecutor")TaskExecutor taskExecutor){
		this.updateStatusService = updateStatusService;
		this.deleteStatusService = deleteStatusService;
		this.searchService = searchService;
		this.taskExecutor = taskExecutor;
	}
	
   @Scheduled(fixedDelay=150000, initialDelay = 0)
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
		   taskExecutor.execute(()-> deleteStatusService.deleteAll());
	   }
   }
}
