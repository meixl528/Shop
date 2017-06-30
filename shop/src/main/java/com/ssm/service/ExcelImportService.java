package com.ssm.service;

import java.util.List;

import com.ssm.dto.Excel;

public interface ExcelImportService {

	List<Excel> getExcelList();
	void excelAdd(Excel excel);
	
}
