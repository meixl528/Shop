package com.ssm.common.mapper;

import java.util.List;

import com.ssm.common.dto.Excel;

import tk.mybatis.mapper.common.Mapper;

public interface ExcelMapper extends Mapper<Excel>{
	
	List<Excel> getExcelList();
	
	void add(Excel excel);
}
