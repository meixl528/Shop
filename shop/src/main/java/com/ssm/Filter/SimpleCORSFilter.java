package com.ssm.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
/**
 * 链接 http://www.itpub.net/thread-1906306-1-1.html
 * 
 * java服务器端配置支持跨域请求
 * http://blog.csdn.net/andong154564667/article/details/51508042
 */
public class SimpleCORSFilter implements Filter {
	
	//可以实现   跨域  ajax 请求
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		System.out.println("req.getRemoteAddr() = "+req.getRemoteAddr());
		System.out.println("req.getRemoteHost() = "+req.getRemoteHost());
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
	
	
}