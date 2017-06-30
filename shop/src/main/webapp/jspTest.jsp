<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Test.DBConnection,Test.User,java.util.List" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
<%
    List<User> list = DBConnection.queryUser();
	for(User u :list){
		
%>		
		<li>UserName:<%=u.getUsername() %> and Phone:<%=u.getPhone() %>  </li>
<%		
	}

%>
</ul>
</body>
</html>