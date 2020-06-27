package com.hello.controllers;

import java.util.Optional;

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
import com.hello.service.DeleteStatusService;
import com.hello.service.UpdateStatusService;

@RestController
@RequestMapping("/body/*")
public class DeleteStatusController {

	private Logger logger = LoggerFactory.getLogger(DeleteStatusController.class);
	
	@Autowired
	private DeleteStatusService deleteStatusService;
	
	@RequestMapping(value = "/DeleteStatus", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateStatus(HttpServletRequest request) {
		
		String start = request.getParameter("start");
		if(start != null) {
			deleteStatusService.setStart(Boolean.parseBoolean(start));
		}
		
		String all =  request.getParameter("all");
		if(all != null) {
			deleteStatusService.setAll(Boolean.parseBoolean(all));
		}
		
		String cnt = request.getParameter("count");
		if(cnt != null) {
			deleteStatusService.setCnt(Integer.parseInt(cnt));
		}
		
		String oneTime = request.getParameter("one_time");
		if(oneTime != null) {
			deleteStatusService.setOneTime(Boolean.parseBoolean(oneTime));
		}
		
		logger.info(all + ", "+ cnt + ", " + start + ", " + oneTime);
	}
}
