<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
</head>
<body>
	<div style="width:100%"><a href="logout.do">退出</a></div>
	<div style="width:100%;height:100%">
		<div style="width:30%;height:100%;align:left;float:left">
			<ul>
				<c:forEach var="menu" items="${list}">
					<li url="${menu.url}" title="${menu.name}" pId="${menu.pId}">${menu.name}</li>
				</c:forEach>
			</ul>
		</div>
		<div style="width:69%;height:100%;align:center;float:right;border-left:1px solid green;">
			<div style="width:80%;padding:200px">
				Welcome 主页    tomcat 6.0.43
			</div>
		</div>
	</div>
	
	<br>
	${user}
	<input type="hidden" value="${name}" id="name"/>
	
	<br>
	<a href="fileupload.jsp">文件上传</a>
	<br>
	<a href="ajaxFileUpload.jsp">ajax文件上传</a>
	<br>
	<a href="excelMap.html">地图搜索</a>
	<br>
	<a href="ajaxKY.jsp">ajaxKY跨域</a>
	
	<br>
	<ul>
		<c:forEach var="u" items="${pageHelper}">
			<li url="" title="${u.name}" pId="${u.id}">${u.name}--${u.pass}--${u.telephone}--${u.address}</li>
		</c:forEach>
	</ul>
	
</body>
<script>
	$.cookie("userName", $("#name").val(), { path: '/', expires: 60000 });
	
</script>
</html>