package com.ssm.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserLoginInterceptor implements HandlerInterceptor {

	//设置允许通过的url
	private List<String> allowedPass;
	public void setAllowedPass(List<String> allowedPass) {
		this.allowedPass = allowedPass;
	}

	// 在请求的方法处理之前执行
	// 如果返回为true则执行下一个拦截器 , 否不执行
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
		// TODO Auto-generated method stub
		/*System.out.println("UserLoginInterceptor");
		String url = req.getRequestURI().toString();
		
		//先判断session中是否有
		Object user = req.getSession().getAttribute("user");
		if (user!=null) {
			return true;
		}
		
		for(String temp : allowedPass){
			if(url.substring(url.lastIndexOf("/")+1).equals(temp)) 
				return true;
		}
		System.out.println("==================false");
		resp.sendRedirect(req.getContextPath()+"/login.jsp");
		return false;*/
		return true;
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
