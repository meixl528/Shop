package com.ssm.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ClearThreadListener implements ServletContextListener{
	
	private ClearThread cth; 

	 public void contextDestroyed(ServletContextEvent e) {  
        if (cth != null && cth.isInterrupted()) {  
        	cth.interrupt();  
        }  
	 }  
	  
     public void contextInitialized(ServletContextEvent e) {  
        if (cth == null) {  
        	cth = new ClearThread();  
        	cth.start(); // servlet 上下文初始化时启动 socket  
        }  
     } 

}
