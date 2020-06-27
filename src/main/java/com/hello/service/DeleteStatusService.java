package com.hello.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import twitter4j.AccountSettings;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class DeleteStatusService {
	
	private Logger logger = LoggerFactory.getLogger(DeleteStatusService.class);
	
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
		setStart(true);
	}
	
	public void deleteAll() {
		Twitter twitter = TwitterFactory.getSingleton();
		//Status status = null;
		List<Status> statusList = new ArrayList<Status>();
		int pageNumber = 1;
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("tweets2.txt"));
			while (true) {
				try {
					int size = statusList.size();
					Paging page = new Paging(pageNumber++, 100);
					statusList = twitter.getUserTimeline(twitter.getScreenName(), page);
					System.out.println("LAK"+ pageNumber +" : " + statusList.size());
					System.out.println("LAKSTRING : " + statusList.toString());
					for(Status status : statusList) {
						bw.write("[LAK]");
						bw.write(statusList.toString());
						twitter.destroyStatus(status.getId());
					}
					if (statusList.size() == size) {
						break;
					}
				} catch (TwitterException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/*try {
			long id = twitter.getId();
			logger.info("Id : " + id);
	        String name = twitter.getScreenName();
	        logger.info("Name : " + name);
	        String policy = twitter.getPrivacyPolicy();
	        logger.info("Policy : " + policy);
	        String termsOfService = twitter.getTermsOfService();
	        logger.info("TermsOfService : " + termsOfService);
	        AccountSettings accountSettings = twitter.getAccountSettings();
	        logger.info("AccountSettings : " + accountSettings.toString());
	        ResponseList<Status> statusList = twitter.getHomeTimeline();
	        for(Status status : statusList) {
	        	logger.info("@" + status.getUser().getScreenName() + " - " + status.getText());
	        }
		} catch (TwitterException e) {
			e.printStackTrace();
		}*/
		/*try {
			List<Status> statuses = twitter.getUserListStatuses(listId, paging)
			status = twitter.destroyStatus();
			logger.info("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			e.printStackTrace();
		}*/
	}
}
