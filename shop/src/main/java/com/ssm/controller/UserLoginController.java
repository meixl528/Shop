package com.ssm.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ssm.dto.User;
import com.ssm.redis.RedisServiceUtil;
import com.ssm.service.SysMenusService;
import com.ssm.service.UserService;

@Controller
public class UserLoginController {

	@Autowired
	private RedisServiceUtil redisService;
	@Autowired
	private UserService userService;
	@Autowired
	HttpSession session;
	
	@RequestMapping("/login")
	public String login(User user,HttpServletResponse response,ModelMap model){
		Logger log = LoggerFactory.getLogger(UserLoginController.class);
		
		JSONObject jsonObject;
		String name = user.getName();
		String pass  = user.getPass();
		//Gson gson = new Gson();
		//redisService.hset("aaa", "bbb", gson.toJson(new User("ccc","ddd")));
		
		user = (User) session.getAttribute("user");
		
		if((name == null||name=="") && (pass==null||pass=="") && user==null){
			return "redirect:/login.jsp";
		}
		if(pass==null){
			name = user.getName();
			pass  = user.getPass();
		}
		if("admin".equals(pass)){
			session.setAttribute("user", new User(name,pass));
			session.setMaxInactiveInterval(60*2);
			try {
				/**存 对象 */
				testRedisWithObject();
				redisService.hset("redis:cache:online",name,name);
			} catch (Exception e) {
				e.printStackTrace();
				if(log.isErrorEnabled()){
					log.error("redis 未启动!");
				}
			}
			/*Cookie cookie = new Cookie("username",name);
			cookie.setMaxAge(3600);
			cookie.setPath("/");
			response.addCookie(cookie);*/
		
			List<User> uList = userService.getUserList();
			model.addAttribute("pageHelper", uList);
			model.addAttribute("list", sysMenusService.listById(1));
			model.addAttribute("user", new User(name,pass));
			
			return "welcome.jsp";
		}
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("/haslogin")
	public String haslogin(){
		System.out.println("haslogin");
		return "ajax.jsp";
	}
	
	@Autowired
	private SysMenusService sysMenusService;
	
	/**
	 * ajax中   contentType : "application/json" 需要后台 使用  @RequestBody 才能取到参数的值
	 * 2016-10-27
	 * @param user
	 * @return
	 */
	@RequestMapping("/ajax")
	@ResponseBody
	public String ajax(@RequestBody User user){
		//return "ajax.jsp";
		return "{\"name\": \""+user.getName()+"\",\"pass\": \""+user.getPass()+"\"}";
	}
	
	public void testRedisWithObject () throws Exception{
		User user = new User("meixl","123456","15021625324","上海");
		
        Map<String, Object> map = convertToMap(user);
        //Map<byte[], byte[]> data = new HashMap<>();
        Map<String, String> dataStr = new HashMap<>();
        map.forEach((k, v) -> {
            // 排除特殊字段
            if (k.charAt(0) == '_') {
                return;
            }
            if (v instanceof java.util.Date) {
                v = ((Date) v).getTime();
            }
            if (v != null) {
                //data.put(k.getBytes("UTF-8"), v.toString().getBytes("UTF-8"));
            	dataStr.put(k, v.toString());
            }
        });
        redisService.hmset("redis:cache:hmset", dataStr);
        setValue("redis:cache:hset", dataStr);
		
		Map<String,String> mapStr = redisService.hmget("redis:cache:hmset");
		System.out.println("redis:cache:hmset -> map1 = "+mapStr);
		
		Class<? extends Object> clazz =Class.forName(mapStr.get("class"));
		Object obj = clazz.newInstance();
		BeanUtils.populate(obj, mapStr);
		System.out.println("User = "+obj);
		
		mapStr = getValue("redis:cache:hset");
		System.out.println("redis:cache:hset -> map2 = "+mapStr);
	}
	
	private void setValue(String key,Map<String,String> value){
		value.forEach((k, v) -> {
			redisService.hset(key, k, v);
		});
	}
	
	private Map<String,String> getValue(String key){
		Map<String,String> map= new HashMap<>();
		Set<String> set = redisService.hkeys(key);
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			String k = it.next();
			String v = redisService.hget(key, k);
			map.put(k,v);
		}
		return map;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Object> convertToMap(Object obj)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (obj instanceof Map) {
            return (Map<String, Object>) obj;
        }
        Map<String, Object> map = PropertyUtils.describe(obj);
        //map.remove("class"); // describe会包含 class 属性,此处无用
        map.put("class", ((Class)map.get("class")).getName());
        
        //Map<String,String> map2 =BeanUtils.describe(obj);
        return map;
    }
	
	
	@RequestMapping("/logout")
	public String logout(){
		if(session.getAttribute("user")!=null){
			redisService.hdel("redis:cache:online",((User)session.getAttribute("user")).getName());
		}
		if(session.getAttribute("user")!=null){
			session.removeAttribute("user");
		}
		session.invalidate();
		return "redirect:/login.jsp";
	}
}
