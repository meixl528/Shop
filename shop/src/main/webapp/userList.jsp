<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- isELIgnored="false" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span><a href="addUser.do">添加</a></span>
	<table width="1000px" align="left">
		<tr>
			<td>ID</td>
			<td>姓名</td>
			<td>密码</td>
			<td>电话</td>
			<td>地址</td>
		</tr>
		<c:forEach var="user" items="${userlist}">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.pass}</td>
				<td>${user.telephone}</td>
				<td>${user.address}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>