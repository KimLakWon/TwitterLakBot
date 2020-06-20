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
public class ConfigController {

	@Autowired
	private UpdateStatusService updateStatusService;
	
	@RequestMapping(value = "/Config", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void message(HttpServletRequest request) {

	}
}
