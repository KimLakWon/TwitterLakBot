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

import com.hello.model.IUAnalysisRequest;
import com.hello.model.UpdateStatusRequest;
import com.hello.service.IUAnalysisService;
import com.hello.service.UpdateStatusService;

@RestController
@RequestMapping("/body/*")
public class IUAnalysisController {

	private Logger logger = LoggerFactory.getLogger(IUAnalysisController.class);
	
	@Autowired
	private IUAnalysisService iuAnalysisService;
	
	@RequestMapping(value = "/IUAnalysis", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String iuAnalysis(HttpServletRequest request) {
		IUAnalysisRequest iuAnalysisRequest = new IUAnalysisRequest();
		
		String start = request.getParameter("start");
		if(start != null) {
			iuAnalysisRequest.setStart(Boolean.parseBoolean(start));
		}
		
		String interval = request.getParameter("interval");
		if(interval != null) {
			iuAnalysisRequest.setInterval(Integer.parseInt(interval));
		}

		String oneTime = request.getParameter("one_time");
		if(oneTime != null) {
			iuAnalysisRequest.setOneTime(Boolean.parseBoolean(oneTime));
		}
		
		iuAnalysisService.setIUAnalysisRequest(iuAnalysisRequest);
		
		logger.info(iuAnalysisRequest.toString());
		
		return iuAnalysisRequest.toString();
	}
}
