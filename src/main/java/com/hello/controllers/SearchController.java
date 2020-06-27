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
import com.hello.service.SearchService;
import com.hello.service.UpdateStatusService;

@RestController
@RequestMapping("/body/*")
public class SearchController {

	private Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/Search", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void search(HttpServletRequest request) {
		boolean start = Boolean.parseBoolean(request.getParameter("start"));
		searchService.setStart(start);
		int count = Integer.parseInt(request.getParameter("count"));
		searchService.setCount(count);
		String query = request.getParameter("query");
		searchService.setQuery(query);
		logger.info(query +", " + count + "," + start);
	}
}
