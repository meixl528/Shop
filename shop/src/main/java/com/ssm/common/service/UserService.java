package com.ssm.common.service;

import java.util.List;

import com.ssm.common.dto.User;
import com.ssm.core.ProxySelf;

public interface UserService extends ProxySelf<UserService>{

	List<User> getUserList();
	void addUser(User user);
	void deleteAllUser();
	
	List<User> get();
	
}
