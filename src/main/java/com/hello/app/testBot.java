package com.hello.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hello.service.UpdateStatusService;

@Component
public class testBot {

	private UpdateStatusService updateStatusService;

	@Autowired
	public testBot(UpdateStatusService updateStatusService){
		this.updateStatusService = updateStatusService;
	}
	
   @Scheduled(fixedDelay=30000, initialDelay = 0)
	public void execute() {
	   System.out.println("Execute Test. " + updateStatusService.getStart());
	   if(updateStatusService.getStart()) {
		   System.out.println("Execute updateStatusService.");
		   updateStatusService.update();
	   }
   }
}
