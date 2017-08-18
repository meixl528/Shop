package com.ssm.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.common.dto.SysMenus;
import com.ssm.common.mapper.SysMenusMapper;
import com.ssm.common.service.SysMenusService;

@Service("sysMenusService")
public class SysMenusServiceImpl implements SysMenusService{

	@Autowired
	private SysMenusMapper sysMenusMapper;
	
	@Override
	public List<SysMenus> listById(SysMenus sysMenus) {
		System.out.println("调用了listById");
		return sysMenusMapper.select(sysMenus);
	}

}
