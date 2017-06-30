package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import com.ssm.service.UserService;
import com.ssm.dto.User;
import com.ssm.mapper.UserMapper;

@Service("userService")
@Transactional
public class UserserviceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	public List<User> getUserList() {
		System.out.println("ProxySelf -> self = "+self());
		PageHelper.startPage(1, 20);
		return userMapper.getUserList();
	}

	public void addUser(User user) {
		userMapper.addUser(user);
	}
	
	public void deleteAllUser() {
		System.out.println("deleteAllUser");
		userMapper.deleteAllUser();
	}

}
