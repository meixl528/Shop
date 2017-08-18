package com.ssm.init;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.common.service.UserService;

/**
 * 项目启动加载 servlet方式   ->  4种方式:http://blog.csdn.net/fcly2013/article/details/19984061
 * @author me
 */
public class InitServlet extends HttpServlet{

	/**
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(InitServlet.class);
	
	@Autowired
	private UserService userService;
	
	@Override  
    public void init(ServletConfig config) {
		logger.info("项目启动时执行有4种方式 : http://blog.csdn.net/fcly2013/article/details/19984061");
        try {  
            super.init();  
        } catch (ServletException e) {  
            e.printStackTrace();  
        }  
        //PropertyConfigurator.configure(ClassLoader.getSystemResource("log4j.properties"));
        System.out.println("================>[Servlet]自动加载启动开始.");  
        System.out.println("log4j配置: https://my.oschina.net/zimingforever/blog/98048");
        logger.info("读取Spring容器中的Bean[此时Bean已加载,可以使用]");
        
        //timer();
        
        logger.info("执行想要的代码  ");
        // 读取Spring容器中的Bean[此时Bean已加载,可以使用]  
        //执行想要的代码  
        // upgradeToVersion();
        System.out.println("================>[Servlet]自动加载启动结束.");  
    }
	
	
/*	public void timer() {
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	      public void run() {
	    	  System.out.println("-------设定要指定任务--------");
	    	  userService.deleteAllUser();
	      }
	    }, 60000, 60000);
	}*/
	
	
/*	private boolean upgradeToVersion(int versionNo) {
		InputStream  reader = this.getClass().getClassLoader().getResourceAsStream("db/version"+versionNo+".sql");
		try {
            byte[] byteArr = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int byteread = 0;
            while ((byteread = reader.read(byteArr)) != -1) {
            	baos.write(byteArr, 0, byteread);
            }
        	String[] sqlArr =  new String(baos.toByteArray(),"UTF-8").trim().split(";");
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e1) {
            }
        }
		return true;
	}*/

}
