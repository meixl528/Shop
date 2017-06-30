package com.ssm.activeMQ.test;


import com.ssm.activeMQ.annotation.Queue;
import com.ssm.activeMQ.listener.IQueueListener;

@Queue(queue="test.activemq.queue2")
public class TestQueue2 implements IQueueListener{

	@Override
	public String getQueue() {
		return null;
	}

	@Override
	public void onQueueMessage(Object message, String queue) {
		System.out.println("接收消息 : message2 = " +message.toString() +" and queue2 = "+queue);
	}

}
