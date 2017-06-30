package com.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * Interceptor 使用模板
 * @author meixl
 *
 */
public class MyInterceptor implements HandlerInterceptor {
	
	// 在请求的方法处理之前执行
	// 如果返回为true则执行下一个拦截器 , 否不执行
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterceptor");
		return false;
	}

	// 在请求的方法执行之后执行
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object obj, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
	}

	// 在DispatCherServlet 处理之后执行 ----清理工作
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object obj, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
