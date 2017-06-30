package com.ssm.message.impl;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.ssm.message.ITopicMessageListener;
import com.ssm.message.QueueMonitor;
import com.ssm.message.TopicMonitor;
import com.ssm.service.Cache;
import com.ssm.service.InitService;

@TopicMonitor(channel ={"topic:cache:reloaded"})
public class TestTopicMonitor implements ITopicMessageListener<String>{
	Logger logger = LoggerFactory.getLogger(TestTopicMonitor.class);
	@Autowired
    private InitService cacheManager;
	
	private StringRedisSerializer redisSerializer = new StringRedisSerializer();
	private String[] publishMessageTo = { "topic:cache:reloaded" };
	
	
	@Override
    public String[] getTopic() {
        return publishMessageTo;
    }

	@Override
    public void onTopicMessage(String message, String pattern) {
		Cache cache = cacheManager.getCache(message);
		System.out.println("onTopicMessage---com.ssm.message.impl.TestTopicMonitor -------------"+message+" & pattern ="+pattern +": "+new DateTime().toString("yyyy-mm-dd HH:mm:ss SSS"));
        if(logger.isDebugEnabled()){
        	logger.debug("TestTopicMonitor");
        }
    }

	public StringRedisSerializer getRedisSerializer() {
		return redisSerializer;
	}

	public void setRedisSerializer(StringRedisSerializer redisSerializer) {
		this.redisSerializer = redisSerializer;
	}

	public String[] getPublishMessageTo() {
		return publishMessageTo;
	}

	public void setPublishMessageTo(String[] publishMessageTo) {
		this.publishMessageTo = publishMessageTo;
	}

}
