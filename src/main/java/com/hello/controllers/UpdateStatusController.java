package com.hello.controllers;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	private UpdateStatusService updateStatusService;
	
	@RequestMapping(value = "/UpdateStatus", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void updateStatus(HttpServletRequest request) {
		boolean start = Boolean.parseBoolean(request.getParameter("start"));
		updateStatusService.setStart(start);
		String message = request.getParameter("message");
		updateStatusService.setMessage(message);
		System.out.println(message +", " + start);
	}
}
