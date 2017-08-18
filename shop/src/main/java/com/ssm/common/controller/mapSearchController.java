package com.ssm.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.baidu.RoadUtil;
import com.ssm.common.dto.Excel;
import com.ssm.common.service.ExcelImportService;

@Controller
public class mapSearchController {
	
	@Autowired
	private ExcelImportService excelImportService;

	@RequestMapping("/excelSearch")
	@ResponseBody
	public List<Excel> search(@RequestParam("address")String address,@RequestParam("circle") Long circle){
		return new RoadUtil().getReturn(address,"",circle,excelImportService);
	}
	
}
