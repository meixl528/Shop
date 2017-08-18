package com.ssm.kafka.components;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class kafkaController{

	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;


	@ResponseBody
	@RequestMapping(value = "/test/shopKafka")
	public void test() throws Exception {
		
		//向kafka发送位置信息
		ListenableFuture<SendResult<String, String>> sendDefault = kafkaTemplate.send("kafka.topic","123456789");
		
		System.out.println(sendDefault.get().getProducerRecord());
	}

}