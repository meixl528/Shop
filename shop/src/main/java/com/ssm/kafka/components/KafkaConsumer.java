package com.ssm.kafka.components;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


/***
 * 
* <p>Title: KafkaConsumer</p>
* <p>Description: 消费kafka用户位置数据和车辆gps数据</p>
* <p>Company: lty</p>
* @author liuhao
* @date 2017年5月5日 下午2:17:41
 */
@Configuration
public class KafkaConsumer {

	//@KafkaListener(topics = "${kafka.topic}")
	@KafkaListener(topics = "kafka.topic")
	public void listen(ConsumerRecord<Integer, String> record) {
		String datacollection = record.value();
		System.out.println("record = "+ datacollection);
	}

	@KafkaListener(topics = "kafka.topic2")
	public void listen2(ConsumerRecord<Integer, String> record) {
		String datacollection = record.value();
		System.out.println("record2 = "+ datacollection);
	}

}