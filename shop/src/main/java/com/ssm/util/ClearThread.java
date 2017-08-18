package com.ssm.util;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.common.service.UserService;
import com.ssm.redis.MethodCacheInterceptor;

public class ClearThread extends Thread{
	
	private Logger logger = Logger.getLogger(ClearThread.class);
	//private ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml"); 
	private final int TIME = 300000;
	
	
	public void run(){
		while(true){
			try {
				Thread.sleep(TIME);
				//UserService userService = (UserService) ctx.get.getBean("userService");
				//System.out.println("线程 -> ClearThread start :"+"userService = "+userService + new Date());
				//userService.deleteAllUser();
				Date date = new Date();
				int minutes = date.getMinutes();
				if(minutes%10==0){
					//RedisService redisService = (RedisService)ctx.getBean("redisService");
					logger.info("10的倍数时间清空redis");
					//redisService.flushDB();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
