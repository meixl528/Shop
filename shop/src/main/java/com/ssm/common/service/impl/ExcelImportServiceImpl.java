package com.ssm.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.common.dto.Excel;
import com.ssm.common.mapper.ExcelMapper;
import com.ssm.common.service.ExcelImportService;

@Service
public class ExcelImportServiceImpl implements ExcelImportService{

	@Autowired
	private ExcelMapper excelMapper;
	
	@Override
	public List<Excel> getExcelList() {
		return excelMapper.getExcelList();
	}
	
	@Override
	public void excelAdd(Excel excel) {
		excelMapper.add(excel);
	}

}
