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

import com.hello.model.DeleteStatusRequest;
import com.hello.service.DeleteStatusService;

@RestController
@RequestMapping("/body/*")
public class DeleteStatusController {

	private Logger logger = LoggerFactory.getLogger(DeleteStatusController.class);
	
	@Autowired
	private DeleteStatusService deleteStatusService;
	
	@RequestMapping(value = "/DeleteStatus", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteStatus(HttpServletRequest request) {
		DeleteStatusRequest deleteStatusRequest = new DeleteStatusRequest();
		
		String start = request.getParameter("start");
		if(start != null) {
			deleteStatusRequest.setStart(Boolean.parseBoolean(start));
		}
		
		String all =  request.getParameter("all");
		if(all != null) {
			deleteStatusRequest.setAll(Boolean.parseBoolean(all));
		}
		
		String cnt = request.getParameter("count");
		if(cnt != null) {
			deleteStatusRequest.setCnt(Integer.parseInt(cnt));
		}
		
		String oneTime = request.getParameter("one_time");
		if(oneTime != null) {
			deleteStatusRequest.setOneTime(Boolean.parseBoolean(oneTime));
		}
		
		deleteStatusService.setDeleteStatusRequest(deleteStatusRequest);
		logger.info(deleteStatusRequest.toString());
		
		return deleteStatusRequest.toString();
	}
}
