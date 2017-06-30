package com.ssm.baidu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LocationUtil {  
	private static final String BAIDU_APP_KEY ="ZPPOUiyTk6X0OywDFmEVGQxRFlYnmZRZ";
    //private static final String BAIDU_APP_KEY = "4ochbbkn5aKzvBlfIWKC0th80GYKDcR6";  
      
    /** 
     * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度) 
     */  
    public static Map<String, String> getLatitude(String address,String city) {  
        try {  
            // 将地址转换成utf-8的16进制  
            address = URLEncoder.encode(address, "UTF-8");  
            // 如果有代理，要设置代理，没代理可注释  
            // System.setProperty("http.proxyHost","192.168.172.23");  
            // System.setProperty("http.proxyPort","3209");  
            
            //http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&city=%E5%8C%97%E4%BA%AC%E5%B8%82&ak=4ochbbkn5aKzvBlfIWKC0th80GYKDcR6
            
            String uri = "http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address="+ address + "&ak=" + BAIDU_APP_KEY;
            if(city!=""&& city!=null){
            	uri = "http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address="+ address +"&city="+city+"&ak=" + BAIDU_APP_KEY;
            }
            URL resjson = new URL(uri);  
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    resjson.openStream()));  
            String res;  
            StringBuilder sb = new StringBuilder("");  
            while ((res = in.readLine()) != null) {  
                sb.append(res.trim());  
            }  
            in.close();  
            String str = sb.toString();  
            System.out.println("return json:" + str);  
            if(str!=null&&!str.equals("")){  
                Map<String, String> map = null;  
                int lngStart = str.indexOf("lng\":");  
                int lngEnd = str.indexOf(",\"lat");  
                int latEnd = str.indexOf("},\"precise");  
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {  
                    String lng = str.substring(lngStart + 5, lngEnd);  
                    String lat = str.substring(lngEnd + 7, latEnd);  
                    map = new HashMap<String, String>();  
                    map.put("lng", lng);  
                    map.put("lat", lat);  
                    return map;  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    public static void main(String args[]) {  
          
        Map<String, String> map = LocationUtil.getLatitude("成都 高新西区西区大道1398号","");  
        if (null != map) {  
            System.out.println(map.get("lng"));  
            System.out.println(map.get("lat"));  
        }  
    }  
}  