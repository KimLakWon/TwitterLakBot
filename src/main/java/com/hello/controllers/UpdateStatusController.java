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

import com.hello.model.UserVO;
import com.hello.service.UpdateStatusService;

@RestController
@RequestMapping("/body/*")
public class UpdateStatusController {

	private Logger logger = LoggerFactory.getLogger(UpdateStatusController.class);
	
	@Autowired
	private UpdateStatusService updateStatusService;
	
	@RequestMapping(value = "/UpdateStatus", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateStatus(HttpServletRequest request) {
		String start = request.getParameter("start");
		if(start != null) {
			updateStatusService.setStart(Boolean.parseBoolean(start));
		}
		
		String message = request.getParameter("message");
		if(message != null) {
			updateStatusService.setMessage(message);
		}
		
		String oneTime = request.getParameter("one_time");
		if(oneTime != null) {
			updateStatusService.setOneTime(Boolean.parseBoolean(oneTime));
		}
		
		logger.info(message +", " + start + ", " + oneTime);
	}
}
