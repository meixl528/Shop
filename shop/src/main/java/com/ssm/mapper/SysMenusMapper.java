package com.ssm.mapper;

import java.util.List;

import com.ssm.dto.SysMenus;

public interface SysMenusMapper {

	List<SysMenus> listById(int id);
	
	void add(SysMenus sm);
	
	boolean delete(int id); 
	
}
