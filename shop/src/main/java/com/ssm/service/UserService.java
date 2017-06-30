package com.ssm.service;

import java.util.List;

import com.ssm.core.ProxySelf;
import com.ssm.dto.User;

public interface UserService extends ProxySelf<UserService>{

	List<User> getUserList();
	void addUser(User user);
	void deleteAllUser();
	
}
