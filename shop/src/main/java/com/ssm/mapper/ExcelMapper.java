package com.ssm.mapper;

import java.util.List;

import com.ssm.dto.Excel;

public interface ExcelMapper {
	
	List<Excel> getExcelList();
	
	void add(Excel excel);
}
