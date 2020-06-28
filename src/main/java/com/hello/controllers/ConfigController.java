package com.hello.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hello.app.Scheduler;

@RestController
@RequestMapping("/body/*")
public class ConfigController {

	private Logger logger = LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	private Scheduler scheduler;
	
	@RequestMapping(value = "/Config", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String config(HttpServletRequest request) {
		String period = request.getParameter("period");
		if(period != null) {
			scheduler.remove();
			scheduler.start(Integer.parseInt(period));
		}
		
		logger.info("period : " + period);
		
		return "period : " + period;
	}
}
