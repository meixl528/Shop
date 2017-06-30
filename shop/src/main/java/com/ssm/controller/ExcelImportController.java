package com.ssm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ssm.baidu.LocationUtil;
import com.ssm.dto.Excel;
import com.ssm.service.ExcelImportService;

@Controller
public class ExcelImportController {

	@Autowired
	private ExcelImportService excelImportServcie;

	@RequestMapping("/excelimport")
	@ResponseBody
	public String fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest req) throws Exception {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		// 鑾峰彇鏂囦欢鍚�
		// file.getOriginalFilename();
		// 鑾峰彇涓婁紶鏂囦欢鐨勮矾寰�
		String path = req.getRealPath("/fileupload");

		InputStream is = file.getInputStream();
		// os = new FileOutputStream(new File(path,file.getOriginalFilename()));
		String uuidName = uuidName(file.getOriginalFilename());
		OutputStream os = new FileOutputStream(new File(path, uuidName));
		int len = 0;
		byte[] byteArr = new byte[1024];
		while ((len = is.read(byteArr)) != -1) {
			os.write(byteArr, 0, len);
		}
		os.close();
		is.close();
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("上传文件共使用时间：" + (endTime - startTime));
		// 璇诲彇excel鏂囦欢鍐呭
		//read(path + "/" + uuidName);
		return "ok";
	}

	/**
	 * 更快的文件上传方式
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/excelimport2")
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long startTime = System.currentTimeMillis(); // 获取开始时间

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				MultipartFile file = multiRequest.getFile(ite.next());
				if (file != null) {
					String path = request.getRealPath("/fileupload");
					String uuidName = uuidName(file.getOriginalFilename());
					File localFile = new File(path, uuidName);
					file.transferTo(localFile); // 将上传文件写到服务器上指定的文件
				}
			}
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("上传文件共使用时间2：" + (endTime - startTime));
		return "ok";
	}

	// 鐢熸垚uuid鏂囦欢鍚�
	private String uuidName(String fileName) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString() + "_" + fileName;
	}

	/**
	 * js -> window.open() 鎵撳紑excel涓嬭浇
	 * 
	 * @param excelName
	 * @param req
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/excelDownload")
	@ResponseBody
	public void excelDownload(@RequestParam("file") String excelName, HttpServletRequest req,
			HttpServletResponse response) throws IOException {
		String path = req.getRealPath("/excelModel");
		File file = new File(path + "/" + excelName);
		FileInputStream inputStream = new FileInputStream(file);
		OutputStream os = response.getOutputStream();

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + excelName);
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		byte[] buffer = new byte[1024];
		int b = inputStream.read(buffer);
		while (b != -1) {
			// 4.鍐欏埌杈撳嚭娴�(out)涓�
			os.write(buffer, 0, b);
			b = inputStream.read(buffer);
		}
		inputStream.close();
		os.flush();
		os.close();
	}

	public void read(String filePath) throws Exception {
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
		InputStream stream = new FileInputStream(filePath);
		Workbook wb = null;
		if (fileType.equals("xls")) {
			try {
				wb = new HSSFWorkbook(stream);
			} catch (Exception e) {
				wb = WorkbookFactory.create(new File(filePath));
			}
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook(stream);
		} else {
			System.out.println("鎮ㄨ緭鍏ョ殑excel鏍煎紡涓嶆纭�");
		}
		Sheet sheet1 = wb.getSheetAt(0);
		int firstRowNUm = sheet1.getFirstRowNum();
		Excel excel;
		for (Row row : sheet1) {
			if (firstRowNUm == row.getRowNum())
				continue;
			for (Cell cell : row) {
				cell.setCellType(1);
				// if(cell.getColumnIndex()==0 || cell.getColumnIndex()==1 )
				// continue;
				// System.out.print(cell.getStringCellValue() + " ");
			}
			excel = new Excel();
			// int firstCellNum = row.getFirstCellNum();
			excel.setHangye(row.getCell(2).getStringCellValue());
			excel.setZhongduankehu(row.getCell(3).getStringCellValue());
			excel.setZigongsi(row.getCell(4).getStringCellValue());
			excel.setProvince(row.getCell(5).getStringCellValue());
			excel.setCity(row.getCell(6).getStringCellValue());
			excel.setAddress(row.getCell(7).getStringCellValue());
			excel.setWork(row.getCell(8).getStringCellValue());
			excel.setProductivity(row.getCell(9).getStringCellValue());
			excel.setIncome(row.getCell(10).getStringCellValue());
			excel.setOutput(row.getCell(11).getStringCellValue());
			excel.setDescription(row.getCell(12).getStringCellValue());

			Map<String, String> map = LocationUtil.getLatitude(excel.getCity()+ excel.getAddress(), "");
			excel.setX(map.get("lng"));
			excel.setY(map.get("lat"));
			// excel.setDistance(distance);
			System.out.println("excle = " + excel.toString());
			excelImportServcie.excelAdd(excel);
		}
	}

}
