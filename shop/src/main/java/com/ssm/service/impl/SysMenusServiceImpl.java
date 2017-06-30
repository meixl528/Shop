package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.service.SysMenusService;
import com.ssm.dto.SysMenus;
import com.ssm.mapper.SysMenusMapper;

@Service("sysMenusService")
public class SysMenusServiceImpl implements SysMenusService{

	@Autowired
	private SysMenusMapper sysMenusMapper;
	
	@Override
	public List<SysMenus> listById(int id) {
		System.out.println("调用了listById");
		return sysMenusMapper.listById(id);
	}

	@Override
	public void add(SysMenus sm) {
		sysMenusMapper.add(sm);
	}

	@Override
	public boolean delete(int id) {
		return sysMenusMapper.delete(id);
	}

}
