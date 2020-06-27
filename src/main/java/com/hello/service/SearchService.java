package com.hello.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class SearchService {
	
	private Logger logger = LoggerFactory.getLogger(SearchService.class);
	
	private String query;
	private int count;
	private boolean start;
	private boolean saveFile;

	public void setQuery(String query) {
		this.query = query;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean getStart() {
		return start;
	}
	public void setSaveFile(boolean saveFile) {
		this.saveFile = saveFile;
	}
	public boolean getSaveFile() {
		return saveFile;
	}
	@PostConstruct
	private void init() {
		setStart(false);
		setSaveFile(false);
		setCount(20);
	}
	
	public void search() {
		logger.info("search");
		Twitter twitter = TwitterFactory.getSingleton();
		BufferedWriter bw = null; 
		try {
			if(query == null) {
				query = "Machine Learning";
			}
			Query searchQuery = new Query(query);
			searchQuery.count(getCount());
			QueryResult result = null;
			 
			result = twitter.search(searchQuery);
			int cnt = 1;
			for (Status status : result.getTweets()) {
				if(getSaveFile()) {
					bw = new BufferedWriter(new FileWriter("tweets.txt"));
				    bw.write(getMessage(status));
				    bw.close();
				}else {
					logger.info((cnt++) + "");
					logger.info(getMessage(status));
				}
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			logger.error("Non-Expected Exception!");
			e.printStackTrace();
		}finally {
			
		}
	}
	protected String getMessage(Status status) {
		return "@" + status.getUser().getScreenName() + ":"
	             + status.getText() + "|||"
	             + status.getRetweetCount() + "\r\n";
	}
}
