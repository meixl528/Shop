package com.ssm.activeMQ.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.ssm.activeMQ.annotation.Topic;
import com.ssm.activeMQ.listener.ITopicListener;

@Topic(channel={"test.activemq.topic","test.activemq.topic2"})
public class TestTopic implements ITopicListener<Message>{


	@Override
	public void onTopicMessage(Message message, String topic) {
		try {
			if(message instanceof TextMessage){
				System.out.println("接收topic消息 : message = " +((TextMessage)message).getText() +" and topic = "+topic);
			}else{
				System.out.println("接收topic消息 : message = " +message +" and topic = "+topic);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getTopic() {
		return null;
	}

}
