<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	hello fileupload

	<!-- 	<form action="upload.do" method="post" enctype="multipart/form-data">
		上传文件:<br>
		文件一 : <input type="file" name="file"><br>
		文件二 : <input type="file" name="file">
		<button type="submit">上传</button>
	</form> -->
	<br>
	<br> excel导入:
	<form action="excelimport.do" method="post"
		enctype="multipart/form-data">
		<input type="file" name="file" id="file">
		<button id="import" type="button">导入</button>
	</form>

	<br>
	<br> excel模板下载:
	<form action="excelDownload" method="post"
		enctype="multipart/form-data">
		<input type="hidden" value="import.xlsx">
		<button id="download" type="button">模板下载</button>
	</form>
</body>
<script>
	$("#import").click(function() {
		//获取file对象
		var file = document.getElementById("file").files[0];
		// FormData 对象
		var form = new FormData();
		form.append("file", file); // 文件对象
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
		/* $.ajax({
			url : 'excelDownload.do?file='+"import.xlsx",
			type : "POST",
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(json) {
				
			},
			error : function() {
				alert("错误")
			}
		}); */
	});
</script>
</html>