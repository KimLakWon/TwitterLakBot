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

import com.hello.model.DeleteStatusRequest;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class DeleteStatusService {

	private Logger logger = LoggerFactory.getLogger(DeleteStatusService.class);

	private DeleteStatusRequest deleteStatusRequest;
	
	public DeleteStatusRequest getDeleteStatusRequest() {
		return deleteStatusRequest;
	}

	public void setDeleteStatusRequest(DeleteStatusRequest deleteStatusRequest) {
		this.deleteStatusRequest = deleteStatusRequest;
	}

	@PostConstruct
	private void init() {
		deleteStatusRequest = new DeleteStatusRequest();
		deleteStatusRequest.setStart(false);
		deleteStatusRequest.setAll(false);
		deleteStatusRequest.setCnt(0);
	}

	public void delete() {
		Twitter twitter = TwitterFactory.getSingleton();
		List<Status> statusList = new ArrayList<Status>();
		int pageNumber = 1;
		int count = deleteStatusRequest.getCnt();
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("deleteStatusLog.txt"));
			while (deleteStatusRequest.isAll() || count > 0) {
				try {
					int pageCount = (deleteStatusRequest.isAll() || count > 50) ? 50 : count;
					Paging page = new Paging(pageNumber++, pageCount);
					count -= pageCount;
					statusList = twitter.getUserTimeline(twitter.getScreenName(), page);
					for (Status status : statusList) {
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
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (deleteStatusRequest.isOneTime()) {
				deleteStatusRequest.setStart(false);
			}
		}
	}
}
