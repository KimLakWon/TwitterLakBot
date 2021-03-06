package com.hello.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hello.model.IUAnalysisRequest;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public class IUAnalysisService {
	
	private Logger logger = LoggerFactory.getLogger(IUAnalysisService.class);

	private IUAnalysisRequest iuAnalysisRequest;
	
	public IUAnalysisRequest getIUAnalysisRequest() {
		return iuAnalysisRequest;
	}

	public void setIUAnalysisRequest(IUAnalysisRequest iuAnalysisRequest) {
		this.iuAnalysisRequest = iuAnalysisRequest;
	}
	
	@PostConstruct
	private void init() {
		iuAnalysisRequest = new IUAnalysisRequest();
		iuAnalysisRequest.setStart(false);
	}
	
	public void analysis() {
		Twitter twitter = TwitterFactory.getSingleton();
		Status status = null;
		SimpleDateFormat  formatter = new SimpleDateFormat();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, iuAnalysisRequest.getInterval() * -1);
		
		Calendar cal2 = Calendar.getInstance();
		
		String query = "아이유";
		int defaultValue = 100;
		boolean isEnd = false;
		try {
			iuAnalysisRequest.setFlag(!iuAnalysisRequest.isFlag());
			
			Query searchQuery = new Query(query);
			QueryResult result = null;
			int totalCount = defaultValue;
			searchQuery.count(totalCount);
			int repeat = 0;
			while(true) {
				result = twitter.search(searchQuery);
				int count = 0;
				for(Status tweet : result.getTweets()) {
					count++;
					if(tweet.getCreatedAt().before(cal.getTime())) {
						isEnd = true;
						break;
					}
				}
				if(isEnd) {
					totalCount += count - repeat;
					break;
				}
				totalCount += count;
				repeat += 1;
				searchQuery.count(totalCount);
				searchQuery.setMaxId(result.getTweets().get(result.getTweets().size()-1).getId());
			}
			formatter.applyPattern("yyyy년 MM월 dd일 hh시 mm분 ss초");
			String prevTime = formatter.format(cal.getTime());
			String currentTime = formatter.format(cal2.getTime());
			logger.info(prevTime + " ~ " + currentTime + "\n- 아이유 관련 트윗수 : " + totalCount + "개");
			status = twitter.updateStatus(prevTime + " ~ " + currentTime + "\n- 아이유 관련 트윗수 : " + totalCount + "개");
			logger.info("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			e.printStackTrace();
		}finally {
			if(iuAnalysisRequest.isOneTime()) {
				iuAnalysisRequest.setStart(false);
			}
		}
	}
}
