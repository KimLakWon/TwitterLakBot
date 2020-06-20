package com.hello.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class UpdateStatusService {
	
	private String message;
	private boolean start;

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
				message = "success!";
			}
			status = twitter.updateStatus(message + "\nnumber: "+ Math.random()*1000000);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
