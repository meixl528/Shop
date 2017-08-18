package com.ssm.baidu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssm.common.dto.Excel;
import com.ssm.common.service.ExcelImportService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RoadUtil {
	private static final String APP_KEY ="ZPPOUiyTk6X0OywDFmEVGQxRFlYnmZRZ";
	//private static final String APP_KEY = "4ochbbkn5aKzvBlfIWKC0th80GYKDcR6";  
	
	/**
	 * 查询中心地址的经纬度,返回Map
	 */
	public Map<String,String> getCenter(String center,String city){
		Map<String,String> map = LocationUtil.getLatitude(center,city);
		return map; 
	}
	
	/**
	 * 查询地址的经纬度,返回经纬度列表
	 */
	public List<Map<String,String>> getXY(ExcelImportService excelImportServcie){
		List<Map<String,String>> list = new ArrayList<>();
		Map<String,String> map = new HashMap<>();
		
		List<Excel> excelList = excelImportServcie.getExcelList();
		for(Excel excel :excelList){
			map = LocationUtil.getLatitude(excel.getCity()+excel.getAddress(),"");
			list.add(map);
		}
		return list; 
	}
	
	
	public List<Excel> getReturn(String center,String city,Long circle,ExcelImportService excelImportServcie){
		try {
			//获取中心位置经纬度
			Map<String,String> map = getCenter(center,city);
			//String address = URLEncoder.encode("新乡市牧野工业电源产业园区18号", "UTF-8");  
			
			//获取其他查询经纬度
			//List<Map<String,String>> list = getXY(excelImportServcie); 
			List<Excel> list = excelImportServcie.getExcelList();
			
			//origins起点多个  , destinations终点多个
			//http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=40.45,116.34|40.54,116.35&destinations=40.34,116.45|40.35,116.46&ak=
			
			//起点
			String origins = map.get("lat")+","+map.get("lng");
			//终点
			String destinations = "";
			
			Map<String,String> resultMap =null;
			URL resjson = null;
			int len = list.size();
			for (int i=0;i<len;i++) {
				destinations = list.get(i).getY()+","+list.get(i).getX();
				
				resjson = new URL("http://api.map.baidu.com/routematrix/v2/driving?output=json&origins="+origins+"&destinations="+destinations+"&ak="+ APP_KEY);
				BufferedReader in = new BufferedReader(new InputStreamReader(  
	                    resjson.openStream()));  
	            String res;  
	            StringBuilder sb = new StringBuilder("");  
	            while ((res = in.readLine()) != null) {  
	                sb.append(res.trim());  
	            }
	            in.close();  
	            String result = sb.toString();  
	            System.out.println("result :" + result);
	            
	            //返回result转json
	            JSONObject json = JSONObject.fromObject(result);
	            Object jsonResult = JSONArray.fromObject(json.get("result"));
	            Object mapObj = JSONArray.fromObject(jsonResult).get(0);
	            
	            Object distance = JSONObject.fromObject(mapObj).get("distance");
	            Object duration = JSONObject.fromObject(mapObj).get("duration");
	            
	            Object distanceText = JSONObject.fromObject(distance).get("text");
	            Object durationText = JSONObject.fromObject(duration).get("text");
	            
	            if(Double.parseDouble(distanceText.toString().substring(0, distanceText.toString().length()-2)) <= circle/1000.0){
	            	list.get(i).setDistance(distanceText.toString());
	            }else{
	            	list.remove(i);
	            	len--;
	            	i--;
	            }
			}
			list.add(new Excel(map.get("lng"),map.get("lat"),center));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		//System.out.println("list = "+ new RoadUtil().getReturn("新乡市牧野工业电源产业园区18号","新乡"));
	}

}
