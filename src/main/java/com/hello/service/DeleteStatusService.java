package com.hello.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	
	private boolean start;
	private int cnt;
	private boolean all;
	private boolean oneTime;
	
	public boolean isOneTime() {
		return oneTime;
	}
	public void setOneTime(boolean oneTime) {
		this.oneTime = oneTime;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public boolean isAll() {
		return all;
	}
	public void setAll(boolean all) {
		this.all = all;
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
		setAll(false);
		setCnt(0);
	}

	public void delete() {
		Twitter twitter = TwitterFactory.getSingleton();
		List<Status> statusList = new ArrayList<Status>();
		int pageNumber = 1;
		int count = getCnt();
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("deleteStatusLog.txt"));
			while (isAll() || count>0) {
				try {
					int pageCount = (isAll() || count>50) ? 50 : count;
					Paging page = new Paging(pageNumber++, pageCount);
					count-=pageCount;
					statusList = twitter.getUserTimeline(twitter.getScreenName(), page);
					for(Status status : statusList) {
						bw.write(status.getText());
						bw.newLine();
						twitter.destroyStatus(status.getId());
					}
					logger.info("Successfully delete status : " + statusList.size());
					if (statusList.size() == 0) {
						break;
					}
				} catch (TwitterException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(isOneTime()) {
				setStart(false);
			}
		}
	}
}
