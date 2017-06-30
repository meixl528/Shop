package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.service.ExcelImportService;
import com.ssm.dto.Excel;
import com.ssm.mapper.ExcelMapper;

@Service("excelImportServcie")
public class ExcelImportImpl implements ExcelImportService{

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
