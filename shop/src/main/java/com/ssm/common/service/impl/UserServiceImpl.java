package com.ssm.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.ssm.common.dto.User;
import com.ssm.common.mapper.UserMapper;
import com.ssm.common.service.UserService;
import com.ssm.core.jndi.db.ReadDataSource;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	public List<User> getUserList() {
		System.out.println("ProxySelf -> self = "+self());
		PageHelper.startPage(1, 20);
		return userMapper.getUserList();
	}

	@Transactional
	public void addUser(User user) {
		userMapper.addUser(user);
	}
	
	@Transactional
	public void deleteAllUser() {
		System.out.println("deleteAllUser");
		userMapper.deleteAllUser();
	}
	
	@ReadDataSource
	@Override
	public List<User> get(){
		return userMapper.getUserList();
	}

}
