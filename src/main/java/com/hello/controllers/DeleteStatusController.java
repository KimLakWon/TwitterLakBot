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
		boolean start = Boolean.parseBoolean(request.getParameter("start"));
		deleteStatusService.setStart(start);
		String message = request.getParameter("message");
		deleteStatusService.setMessage(message);
		logger.info(message +", " + start);
	}
}
