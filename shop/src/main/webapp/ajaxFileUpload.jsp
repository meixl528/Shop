<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<title>Insert title here</title>
</head>
<body>
	hello fileupload

	<br> excel导入:
	<input type="file" name="file" id="file">
	<button id="import" type="button">导入</button>
	<button id="import2" type="button">导入2</button>
	
	<br>
	<br> excel模板下载:
	<input type="hidden" value="import.xlsx">
	<button id="download" type="button">模板下载</button>
</body>
<script>
	$("#import").click(function() {
	    var filePath = document.getElementById("file").value;
	    var ds=filePath.toString();
	    var extend = filePath.substring(filePath.lastIndexOf("."), filePath.length);
	   // if (extend == ".xlsx" || extend == ".xls" ) 
	   // {
	    	  $.ajaxFileUpload({
	              url : 'excelimport.do',
	             // url: '/upload.aspx', //用于文件上传的服务器端请求地址
	              secureuri: false, //是否需要安全协议，一般设置为false
	              fileElementId: 'file', //文件上传域的ID
	              //dataType: 'json', //返回值类型 一般设置为json
	              success: function (e)  //服务器成功响应处理函数
	              {
	            	  alert(" success");
	              },
	              error: function (e)//服务器响应失败处理函数
	              {
	                  alert("failed");
	              }
	          }); 
	   // }
	})

	$("#import2").click(function() {
		var filePath = document.getElementById("file").value;
	    var ds=filePath.toString();
	    var extend = filePath.substring(filePath.lastIndexOf("."), filePath.length);
	    //if (extend == ".xlsx" || extend == ".xls" ) 
	    //{
	    	  $.ajaxFileUpload({
	              url : 'excelimport2.do',
	             // url: '/upload.aspx', //用于文件上传的服务器端请求地址
	              secureuri: false, //是否需要安全协议，一般设置为false
	              fileElementId: 'file', //文件上传域的ID
	              //dataType: 'json', //返回值类型 一般设置为json
	              success: function (e)  //服务器成功响应处理函数
	              {
	            	  alert(" success2");
	              },
	              error: function (e)//服务器响应失败处理函数
	              {
	                  alert("failed2");
	              }
	          }); 
	    //}
	})

	$("#import1").click(function() {
		//获取file对象
		var file = document.getElementById("file").files[0];
		// FormData 对象
		var form = new FormData();
		form.append("file", file); // 文件对象
		form.append("something","something");
		// XMLHttpRequest 对象
		var xhr = new XMLHttpRequest();
		xhr.open("post", "excelimport.do", true);
		xhr.send(form);
		xhr.onload = function() {
			if(xhr.readyState == 4 && xhr.status == 200){    
				var b = xhr.responseText;  
				alert(b)
			};
		};
	});

	$("#download").click(function() {
		window.open("excelDownload.do?file=" + "import.xlsx");
	});
</script>
</html>