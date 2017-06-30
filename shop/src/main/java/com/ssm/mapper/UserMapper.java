package com.ssm.mapper;

import java.util.List;

import com.ssm.dto.User;

public interface UserMapper {
	
	List<User> getUserList();
	
	void addUser(User user);
	void deleteAllUser();
	
	List<User> getUserListLimit();
}
