package com.ssm.common.service;

import java.util.List;

import com.ssm.common.dto.Excel;

public interface ExcelImportService {

	List<Excel> getExcelList();
	void excelAdd(Excel excel);
	
}
