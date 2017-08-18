package com.ssm.message.impl;

import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.ssm.common.service.Cache;
import com.ssm.common.service.InitService;
import com.ssm.message.IMessagePublisher;
import com.ssm.message.IQueueMessageListener;
import com.ssm.message.ITopicMessageListener;
import com.ssm.message.QueueMonitor;
import com.ssm.message.TopicMonitor;
/**
 * 用来接收<b>队列</b>消息, reload一个Cache.
 * <p>
 * 在集群环境中,只会有一个节点收到消息,避免重复的reload.<br/>
 * 当reload完毕后,发出一个广播消息,频道[topic:cache.reload] ,消息 [cacheName].<br>
 * 同时订阅这个消息,并对相应的cache 执行onCacheReload()(这个操作会在集群中的每个节点上执行)
 */

@QueueMonitor(queue ="queue:cache:reload")
@TopicMonitor(channel ={"topic:cache:reloaded"})
public class TestMonitorServiceImpl implements IQueueMessageListener<String>,ITopicMessageListener<String>{
	Logger logger = LoggerFactory.getLogger(TestMonitorServiceImpl.class);

	@Autowired
    private InitService cacheManager;
	@Autowired
    private IMessagePublisher messagePublisher;
	
	private String name;
	private StringRedisSerializer redisSerializer = new StringRedisSerializer();
	private String[] publishMessageTo = { "queue:cache:reload" };
	
	
	public IMessagePublisher getMessagePublisher() {
		return messagePublisher;
	}

	public void setMessagePublisher(IMessagePublisher messagePublisher) {
		this.messagePublisher = messagePublisher;
	}

	public String[] getPublishMessageTo() {
		return publishMessageTo;
	}

	public void setPublishMessageTo(String[] publishMessageTo) {
		this.publishMessageTo = publishMessageTo;
	}

	public void setRedisSerializer(StringRedisSerializer redisSerializer) {
		this.redisSerializer = redisSerializer;
	}

	@Override
    public String getQueue() {
        return name;
    }
	public void setQueue(String name) {
        this.name = name;
    }

	@Override
    public RedisSerializer<String> getRedisSerializer() {
        return redisSerializer;
    }

	@Override
    public void onQueueMessage(String cacheName, String queue) {
        @SuppressWarnings("rawtypes")
		Cache cache = cacheManager.getCache(cacheName);
        System.out.println("onQueueMessage--------cacheName = "+cacheName+" & queue ="+queue+": "+ new DateTime().toString("yyyy-MM-dd HH:mm:ss SSS"));
        if (cache != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("begin reload cache:" + cacheName);
            }
            //cache.reload();
            /*for (String top : publishMessageTo) {
                messagePublisher.publish(top, cacheName);
            }*/
            if (logger.isDebugEnabled()) {
                logger.debug("reload cache:{} success.", cacheName);
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("{} is not a valid cache.", cacheName);
            }
        }
    }

	@Override
    public String[] getTopic() {
        return publishMessageTo;
    }

	@Override
    public void onTopicMessage(String message, String pattern) {
		Cache cache = cacheManager.getCache(message);
		System.out.println("onTopicMessage----com.ssm.message.impl.TestMonitorServiceImpl --------"+message+" & pattern ="+pattern+": "+ new DateTime().toString("yyyy-MM-dd HH:mm:ss SSS"));
        if(logger.isDebugEnabled()){
        	logger.debug("TestMonitorServiceImpl");
        }
    }
	
    
    //queue publish
    public void queueList(String cacheName, String queue){
    	messagePublisher.rPush(queue,cacheName);
    	/*for (String q : publishMessageTo) {
    		messagePublisher.rPush(q,"userCache");
        }*/
    }
    
    //topic publish
    public void topicPublish(String cacheName, String topic){
    	messagePublisher.publish(topic, cacheName);
    	/*for (String topic : publishMessageTo) {
            messagePublisher.publish(top, cacheName);
        }*/
    }

}
