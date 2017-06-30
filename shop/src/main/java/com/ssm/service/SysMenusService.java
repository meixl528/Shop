package com.ssm.service;

import java.util.List;

import com.ssm.dto.SysMenus;

public interface SysMenusService {

	List<SysMenus> listById(int id);
	
	void add(SysMenus sm);
	
	boolean delete(int id); 
}
