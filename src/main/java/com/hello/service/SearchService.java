package com.hello.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hello.model.SearchRequest;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class SearchService {
	
	private Logger logger = LoggerFactory.getLogger(SearchService.class);
	

	private SearchRequest searchRequest;
	
	public SearchRequest getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(SearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

	@PostConstruct
	private void init() {
		searchRequest = new SearchRequest();
		searchRequest.setStart(false);
		searchRequest.setSaveFile(false);
		searchRequest.setCount(20);
	}
	
	public void search() {
		logger.info("search");
		Twitter twitter = TwitterFactory.getSingleton();
		
		try {
			if(searchRequest.getQuery() == null) {
				searchRequest.setQuery("Machine Learning");
			}
			Query searchQuery = new Query(searchRequest.getQuery());
			searchQuery.count(searchRequest.getCount());
			QueryResult result = null;
			 
			result = twitter.search(searchQuery);
			
			if(searchRequest.isSaveFile()) {
				SaveFileStatus(result);
			}else {
				PrintLogStatus(result);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("Non-Expected Exception!");
			e.printStackTrace();
		}finally {
			if(searchRequest.isOneTime()) {
				searchRequest.setStart(false);
			}
		}
	}
	
	private void PrintLogStatus(QueryResult result) {
		int cnt = 1;
		for (Status status : result.getTweets()) {
			logger.info((cnt++) + ".");
			logger.info(getMessage(status));
		}
	}
	
	private void SaveFileStatus(QueryResult result) throws  IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("searchResult.txt"));
		for (Status status : result.getTweets()) {
			    bw.write(getMessage(status));
		}
		bw.close();
	}
	
	private String getMessage(Status status) {
		return "@" + status.getUser().getScreenName() + ":"
	             + status.getText() + "|||"
	             + status.getRetweetCount() + "\r\n";
	}
}
