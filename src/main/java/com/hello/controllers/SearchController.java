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

import com.hello.model.SearchRequest;
import com.hello.service.SearchService;

@RestController
@RequestMapping("/body/*")
public class SearchController {

	private Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/Search", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String search(HttpServletRequest request) {
		SearchRequest searchRequest = new SearchRequest();
		
		String start = request.getParameter("start");
		if(start != null) {
			searchRequest.setStart(Boolean.parseBoolean(start));
		}
		
		String count = request.getParameter("count");
		if(count != null) {
			searchRequest.setCount(Integer.parseInt(count));
		}
			
		String query = request.getParameter("query");
		if(query != null) {
			searchRequest.setQuery(query);
		}
		
		String saveFile = request.getParameter("save_file");
		if(saveFile != null) {
			searchRequest.setSaveFile(Boolean.parseBoolean(saveFile));
		}
		
		String oneTime = request.getParameter("one_time");
		if(oneTime != null) {
			searchRequest.setOneTime(Boolean.parseBoolean(oneTime));
		}
		
		searchService.setSearchRequest(searchRequest);
		
		logger.info(searchRequest.toString());
		
		return searchRequest.toString();
	}
}
