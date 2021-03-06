package com.hello.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hello.model.UpdateStatusRequest;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class UpdateStatusService {
	
	private Logger logger = LoggerFactory.getLogger(UpdateStatusService.class);

	private UpdateStatusRequest updateStatusRequest;
	
	public UpdateStatusRequest getUpdateStatusRequest() {
		return updateStatusRequest;
	}

	public void setUpdateStatusRequest(UpdateStatusRequest updateStatusRequest) {
		this.updateStatusRequest = updateStatusRequest;
	}
	
	@PostConstruct
	private void init() {
		updateStatusRequest = new UpdateStatusRequest();
		updateStatusRequest.setStart(false);
	}
	
	public void update() {
		Twitter twitter = TwitterFactory.getSingleton();
		Status status = null;
		try {
			if(updateStatusRequest.getMessage() == null) {
				updateStatusRequest.setMessage("default message.");
			}
			String endString = null;
			if(updateStatusRequest.isFlag()) {
				endString = ".\n";			
			}else {			
				endString = "\n";
			}
			status = twitter.updateStatus(updateStatusRequest.getMessage() + endString);
			updateStatusRequest.setFlag(!updateStatusRequest.isFlag());
			logger.info("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			e.printStackTrace();
		}finally {
			if(updateStatusRequest.isOneTime()) {
				updateStatusRequest.setStart(false);
			}
		}
	}
}
