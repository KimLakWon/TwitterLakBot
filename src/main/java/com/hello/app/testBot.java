package com.hello.app;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Component
public class testBot {

   @Scheduled(fixedDelay=30000, initialDelay = 30000)
	public void execute() {
	   System.out.println("Scheduling Test");
		/*Twitter twitter = TwitterFactory.getSingleton();
	    Query query = new Query("#sanbon");
	    query.setCount(30);
	    QueryResult result = null;
	   try {
		   result = twitter.search(query);
		   System.out.println("&& : " + result.getCount());
			    int i=0;
			    for (Status status : result.getTweets()) {
			        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			    }
	   }catch(TwitterException  e) {
		   e.printStackTrace();
	   }*/
	   Twitter twitter = TwitterFactory.getSingleton();
	   Status status = null;
	   try {
	      status = twitter.updateStatus("test " + Math.random()*1000000);
	   } catch (TwitterException e) {
		   e.printStackTrace();
	   }
	}
}
