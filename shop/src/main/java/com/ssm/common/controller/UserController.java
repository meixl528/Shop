package com.ssm.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.dto.User;
import com.ssm.common.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/userlist")
	public String getUserList(ModelMap model){
		model.addAttribute("userlist",userService.getUserList());
/*		for (User user :userService.getUserList()) {
			System.out.println(user.toString());
		}*/
		return "/userList.jsp";
	}
	
	@RequestMapping("/addUser")
	public String addUser(){
		User user = new User("梅小龙"+Math.ceil(Math.random()*1000),Math.ceil(Math.random()*1000)+"",
				"13554081249","梅陇十村"+Math.ceil(Math.random()*1000));
		userService.addUser(user);
		return "redirect:/userlist.do";
	}
	
	@RequestMapping(value = "/db")
	@ResponseBody
	public List<User> get(){
		return userService.get();
	}
}
