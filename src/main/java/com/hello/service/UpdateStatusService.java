package com.hello.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class UpdateStatusService {
	
	private Logger logger = LoggerFactory.getLogger(UpdateStatusService.class);
	
	private String message;
	private boolean start;
	private boolean oneTime;
	
	public boolean isOneTime() {
		return oneTime;
	}
	public void setOneTime(boolean oneTime) {
		this.oneTime = oneTime;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean getStart() {
		return start;
	}
	@PostConstruct
	private void init() {
		setStart(false);
	}
	
	public void update() {
		Twitter twitter = TwitterFactory.getSingleton();
		Status status = null;
		try {
			if(message == null) {
				message = "default message.";
			}
			status = twitter.updateStatus(message + "\n"+ Math.random()*1000000);
			logger.info("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			e.printStackTrace();
		}finally {
			if(isOneTime()) {
				setStart(false);
			}
		}
	}
}
