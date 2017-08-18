package com.ssm.common.mapper;

import java.util.List;

import com.ssm.common.dto.User;

import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{
	
	List<User> getUserList();
	
	void addUser(User user);
	void deleteAllUser();
	
	List<User> getUserListLimit();
}
